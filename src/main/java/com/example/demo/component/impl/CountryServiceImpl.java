package com.example.demo.component.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.bean.CountryModel;
import com.example.demo.component.CountryService;
import com.example.demo.dao.CountryModelDao;

@Service
@CacheConfig(cacheNames="zookfish")
public class CountryServiceImpl implements CountryService{
	
	@Autowired
	private CountryModelDao countryModelDao;
	
	@Cacheable(key = "#p0")
	@Override
	public CountryModel select(Integer id) {
		System.out.println("查询了缓存");
		CountryModel model = countryModelDao.findById(id);
		System.out.println(model);
		return model;
	}
	
	@CachePut(key ="#p0.id")
	@Override
	public CountryModel update(CountryModel countryModel) {
		System.out.println("更新缓存");
		return countryModelDao.save(countryModel);
	}
	
	@CacheEvict(key = "#p0")
	@Override
	public String delete(Integer id) {
		System.out.println("清除指定缓存id:" + id);
		return "清除缓存";
	}

}
