package com.example.demo;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.bean.Country;
import com.example.demo.bean.CountryModel;
import com.example.demo.component.JmsComponent;
import com.example.demo.dao.CountryDao;
import com.example.demo.dao.CountryModelDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Boot01ApplicationTests {

	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private CountryModelDao countryModelDao;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;
	
	@Autowired
	private JmsComponent jmsComponent;

	@Test
	public void insert() {
		Country country = new Country();
		country.setCountryName("测试");
		country.setCountryCode("cs");
		int result = countryDao.insert(country);
		System.out.println(result);
	}
	
	@Test
	public void insertJpa() {
		CountryModel country = new CountryModel();
		country.setCountryname("测试jpa");
		country.setCountrycode("csjpa");
		countryModelDao.save(country);
	}
	
	
	@Test
	public void delete() {
		
		countryModelDao.delete(186);
	}
	
	
	
	
	@Test
	public void update() {
		
		CountryModel model = new CountryModel();
		model.setId(187);
		model.setCountryname("jpa更新_after");
		model.setCountrycode("update JPa after");
		countryModelDao.save(model);
	}
	
	@Test
	public void select() {
		System.out.println(countryModelDao.findById(187));
//		countryModelDao.findByCountryname("jpa更新_after");
//		
//		Page<CountryModel> resule = countryModelDao.findAll(new PageRequest(1, 20, new Sort(new Order(Direction.DESC,"id"))));
//		System.out.println(resule);
//		System.out.println(resule.getContent());
		
		System.out.println(countryModelDao.findBySome(new PageRequest(1, 20)));
	}
	
	
	// redis测试
	@Test
	public void testRedis() {
		
		stringRedisTemplate.opsForValue().set("name", "zookfish");
		System.out.println(stringRedisTemplate.opsForValue().get("name"));
		System.out.println(stringRedisTemplate.opsForValue().get("age"));
		
		CountryModel m1 = new CountryModel();
		m1.setId(3);
		m1.setCountryname("xiantao");
		m1.setCountrycode("鄂M");
		redisTemplate.opsForValue().set(m1.getCountryname(), m1);
//		System.out.println(redisremplate.opsForValue().get("xiantao").getId());
		
		
	}
	
	@Test
	public void testJms() {
		jmsComponent.send("hello : zookfish");
	}
	
	@Test
	public void contextLoads() {
	}

}
