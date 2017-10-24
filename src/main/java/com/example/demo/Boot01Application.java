package com.example.demo;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import com.example.demo.filter.DemoFilter;
import com.example.demo.listener.DemoListener;
import com.example.demo.servlet.DemoServlet;

@SpringBootApplication
// 该注解提供 springboot 自动扫描 @WebServlet @WebFilter @WebListener 的类注册为 servlet filter listener 第三种方式
@ServletComponentScan
// 开启缓存
@EnableCaching
// 开启jms
@EnableJms
public class Boot01Application implements ServletContextInitializer{
	
	
	// 第一种方式注册servlet filter listener
	/*@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		
		return new ServletRegistrationBean(new DemoServlet(), "/zookfish");
	}
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		return new FilterRegistrationBean(new DemoFilter(), servletRegistrationBean());
	}
	@Bean
	public ServletListenerRegistrationBean servletListenerRegistrationBean() {
		return new ServletListenerRegistrationBean(new DemoListener());
	}*/
	
	public static void main(String[] args) {
		SpringApplication.run(Boot01Application.class, args);
	}
	
	// 第二种方式添加自定义的servlet filter listener
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
//		servletContext.addServlet("demoServlet", new DemoServlet()).addMapping("/zookfish");
//		servletContext.addFilter("demofilter", new DemoFilter()).addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, "demoServlet");
//		servletContext.addListener(new DemoListener());
	}

}
