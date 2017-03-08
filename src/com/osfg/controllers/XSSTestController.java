package com.osfg.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 
 * @author athakur
 *
 */
@Controller
public class XSSTestController {
	
	Logger logger = LoggerFactory.getLogger(XSSTestController.class);
	public static final String QUERY = "q";
	
	@RequestMapping(value="/testXss.htm",method = RequestMethod.POST)
	public String testXSS(HttpServletRequest request, ModelMap modelMap) {
		String query = request.getParameter(QUERY);
		logger.debug("In testXSS method, Query : {}", query);
		//modelMap.put(QUERY, query);
		return "testXss";
	}
	
	@RequestMapping(value="/testXssProtect.htm",method = RequestMethod.POST)
	public String testXssProtect(HttpServletRequest request, ModelMap modelMap) {
		String query = request.getParameter(QUERY);
		logger.debug("Query posy encode : {} ", query);
		//modelMap.put(QUERY, query);
		return "testXss";
	}
	
	@RequestMapping(value="/testXss.htm",method = RequestMethod.GET)
	public String getTestXssPage(HttpServletRequest request, ModelMap modelMap) {
		logger.debug("In getTestXssPage method");
		return "testXss";
	}

}
