package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * @author zookfish
 * 方式一: 处理异常
 * 
 */
//@Controller
//@RequestMapping("/error")
//public class BaseErrorController implements ErrorController {
//
//	private static final Logger log = LoggerFactory.getLogger(BaseErrorController.class);
//
//	@Override
//	public String getErrorPath() {
//		log.error("进入自定义的异常路径");
//		return "error/wrong";
//	}
//
//	@RequestMapping
//	public String error() {
//		return getErrorPath();
//	}
//
//}
