package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName="demoFilter",urlPatterns="/*")
public class DemoFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("demofilter destory");
		
	}

	@Override
	public void doFilter(ServletRequest res, ServletResponse req, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("do demofilter ");
		chain.doFilter(res, req);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		System.out.println("demofilter init");
	}
	
	
}
