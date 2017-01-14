package com.osfg.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WebRequestInterceptor extends HandlerInterceptorAdapter {
	
	Logger logger = LoggerFactory.getLogger(WebRequestInterceptor.class);
	
	
	 @Override
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
	        logger.debug("Request received : {} from IP : {}", request.getRequestURL().toString(),getIncomingConnectionIpAddress(request));
	        return true;
	    }
	 
	 
	 private String getIncomingConnectionIpAddress(HttpServletRequest request) {
		 String ipAddress = request.getHeader("X-FORWARDED-FOR");  
	       if (ipAddress == null) {  
	         ipAddress = request.getRemoteAddr();  
	       }
	       return ipAddress;
	 }

}
