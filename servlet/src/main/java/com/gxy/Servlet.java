package com.gxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/**
		 * 1.req属性学习,req的属性看HttpServletRequest接口有多少属性
		 */
		
		System.out.println("处理get请求");
		if(req == null) {
			System.out.println("req is null");
		}
		String parameter = req.getParameter("name");
		System.out.println(parameter);
		Cookie[] cookies = req.getCookies();
		//1.不能用 cookies.length 当cookies 为null时,会报空指针的
		if(cookies != null) {
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName()+":"+cookie.getValue());
			}
		}
		System.out.println(parameter);
		
		//2.Uri 与 url的区别  一般使用url.
		String requestURI = req.getRequestURI();
		StringBuffer requestURL = req.getRequestURL();
		System.out.println(requestURI + ":"+requestURL);
		
		//3.getQuering 与 getPath
		String queryString = req.getQueryString();
		String servletPath = req.getServletPath();
		System.out.println(queryString + ":"+servletPath);
		
		
		/*
		 * 2.resp属性学习 1.req的属性看HttpServletResponse接口有多少属性  
		 */
		// 1.resp 向页面写数据 getWriter是用字符
		//PrintWriter writer = resp.getWriter();
		//writer.write("hellow");
		
		// 2.使用字节向页面写数据  ,writer与outPutstream不能同时使用.
		ServletOutputStream outputStream = resp.getOutputStream();
		
		outputStream.write("abs".getBytes());
	
		
		
		
	}
	
	
	
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("开始处理");
//	}
	
	@Override
	public void destroy() {
		System.out.println("开始结束");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		Enumeration initParameterNames = config.getInitParameterNames();
		while(initParameterNames.hasMoreElements()) {
			Object nextElement = initParameterNames.nextElement();
			String value = config.getInitParameter((String) nextElement);
			System.out.println(nextElement+value);
		}
		
	}

}
