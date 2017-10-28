package com.example.demo.util;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZookeeperSession {

	private static CountDownLatch countDownLatch = new CountDownLatch(1);

	private ZooKeeper zooKeeper;

	private ZookeeperSession() {

		try {
			// 创建一步的连接
			this.zooKeeper = new ZooKeeper("192.168.237.128:2181,192.168.237.131:2181,192.168.237.132:2181", 5000,
					new ZookeeperWatcher());

			System.out.println(zooKeeper.getState());

			countDownLatch.await();

			System.out.println("已经建立连接");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 获取锁
	public void acquireLock(Long id) {
		String path = "/zookfish-lock" + id;
		try {
			// zookeeper只会创建一个 指定的临时节点 
			zooKeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			System.out.println("成功获取锁: " + path);

		} catch (Exception e) {
			System.out.println("获取锁失败了！！！！");
			// 如果获取锁失败就会抛异常
			int count = 0;
			while (true) {

				try {
					Thread.sleep(200);
					zooKeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
				} catch (Exception e1) {
					count++;
					continue;
				}
				System.out.println("经过  " + count + "次获取到了锁");
				break;
			}
		}
	}
	
	// 释放锁
	public void releaseLock(Long id) {
		String path = "/zookfish-lock" + id;
		try {
			zooKeeper.delete(path, -1);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private class ZookeeperWatcher implements Watcher {

		@Override
		public void process(WatchedEvent event) {
			System.out.println("recieve Watched event: " + event.getState());
			if (KeeperState.SyncConnected == event.getState()) {
				countDownLatch.countDown();
			}

		}
	}
	
	private static class Singleton {
		
		private static ZookeeperSession instance = null;
		static {
			instance = new ZookeeperSession();
		}
		
		public static ZookeeperSession getInstance() {
			return instance;
		}
		
	}
	
	

	public static ZookeeperSession getInstance() {
		return Singleton.getInstance();
	}

	public static void main(String[] args) {
		ZookeeperSession session = ZookeeperSession.getInstance();
		ZookeeperSession session2 = ZookeeperSession.getInstance();
		session.acquireLock(1000L);
		System.out.println("===============1================");
		session2.acquireLock(1000L);
		System.out.println(">>>>>>>>>>>>>>>2<<<<<<<<<<<<<<<<");
		session.releaseLock(1000L);
		
		System.out.println(session == session2);
		
		
	}

}
