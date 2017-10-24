package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/web")
public class WebController {
	
	private static final Logger log = LoggerFactory.getLogger(WebController.class);
	
	@RequestMapping("/io")
	public ModelAndView webIndex() {
		log.info("/web/io视图");
		ModelAndView mv = new ModelAndView("io");
		mv.addObject("title", "zookfish");
		return mv;
	}
}
