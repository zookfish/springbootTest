package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.CountryModel;
import com.example.demo.component.CountryService;

@RestController
@RequestMapping("/cache")
public class TestCacheController {
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping("/get")
	public CountryModel select(@RequestParam(defaultValue="1") Integer id) {
		return countryService.select(id);
	}
	
	@RequestMapping("/upd")
	public CountryModel upd() {
		CountryModel model = new CountryModel();
		model.setId(3);
		model.setCountryname("缓存jpafasdfasdfadfasdf");
		model.setCountrycode("哈哈哈哈哈");
		
		return countryService.update(model);
	}
	
	@RequestMapping("/del")
	public String del(@RequestParam(defaultValue="1")Integer id) {
		return countryService.delete(id);
	}
	
}
