package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/index")
public class IndexController {
	
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@Value("${app.secret}")
	private String secret;
	
	@Value("${app.number}")
	private int number;
	
	@Value("${app.desc}")
	private String desc;
	
	@RequestMapping
	public String index() {
		return "hello zookfish";
	}
	
	
	@RequestMapping("/get")
	public ModelAndView get(@RequestParam String name) {
		log.info("--------------start");
		ModelAndView mv = new ModelAndView("leaf");
		mv.addObject("name", name);
		mv.addObject("age", number);
		mv.addObject("sex", "male");
		mv.addObject("secret", secret);
		mv.addObject("desc", desc);
		mv.addObject("date", new Date());
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		mv.addObject("th", list);
		log.info("---------------end");
		return mv;
	}
	
	@RequestMapping("/err")
	public String test5xx() {
		throw new RuntimeException("502服务器端错误页面");
		
	}
}
