package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 方式三 : 利用spring mvc的特性构建全局统一处理异常  这种用来处理服务端的页面错误
 * 而public error用来处理访问资源类型的页面错误
 * @author Administrator
 *
 */
@ControllerAdvice
public class BaseExceptionHandle {
	
	private static final Logger log = LoggerFactory.getLogger(BaseExceptionHandle.class);
	
	@ExceptionHandler({RuntimeException.class})
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public ModelAndView runtimeException(RuntimeException e) {
		log.info("Runtime类型的异常");
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e.getMessage());
		mv.setViewName("error/500");
		return mv;
	}
	
	@ExceptionHandler({Exception.class})
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public ModelAndView runtimeException(Exception e) {
		log.info("Exception类型的异常");
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e.getMessage());
		mv.setViewName("error/500");
		return mv;
	}
	
	
	@ExceptionHandler({Error.class})
	@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
	public ModelAndView runtimeException(Error e) {
		log.info("Exception类型的异常");
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e.getMessage());
		mv.setViewName("error/500");
		return mv;
	}
}
