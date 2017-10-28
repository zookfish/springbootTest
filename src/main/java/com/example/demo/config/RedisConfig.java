package com.example.demo.config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
public class RedisConfig<K, V> extends CachingConfigurerSupport {
	// @Bean
	// JedisConnectionFactory jedisConnectionFactory() {
	// return new JedisConnectionFactory();
	// }

	// 这里注册指定特定名称的redisTemplate
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		// 自定义缓存管理器 .设置缓存策略
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(200);
		Map<String, Long> expires = new HashMap<>();
		expires.put("zookfish", 200L);
		cacheManager.setExpires(expires);
		return cacheManager;

	}

	@Override
	public KeyGenerator keyGenerator() {
		// TODO Auto-generated method stub
		return new KeyGenerator() {

			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}

		};
	}
	
	// 注册redis集群操作bean
	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.237.128", 7001));
		nodes.add(new HostAndPort("192.168.237.131", 7003));
		nodes.add(new HostAndPort("192.168.237.132", 7005));
		return new JedisCluster(nodes);
	}
	
	/**
	 * 自定义 key. 此方法将会根据类名+方法名+所有参数的值生成唯一的一个 key,即使@Cacheable 中 的 value 属性一样， key
	 * 也会不一样。
	 */

//	 @Bean(name="redisTemplate")
//	 public RedisTemplate<K, V> redisTemplate(RedisConnectionFactory factory)
//	 {
//	 RedisTemplate<K, V> template = new RedisTemplate<K, V>();
//	 template.setConnectionFactory(factory);
//	// template.setKeySerializer(new StringRedisSerializer());
//	 template.setValueSerializer(new RedisObjectSerializer());
//	 return template;
//	 }
}