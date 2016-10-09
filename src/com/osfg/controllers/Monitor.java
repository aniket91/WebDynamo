package com.osfg.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 
 * @author athakur
 * Test class for Monitor. Can be used to testing index and error pages.
 */
@Controller
public class Monitor {
	
	Logger logger = LoggerFactory.getLogger(Monitor.class);
	
	@RequestMapping(value="/index.htm",method=RequestMethod.GET)
	public String testIndex() {
		logger.debug("Entered Controller : {} and method : {}", "Monitor", "testIndex");
		return "index";
	}
	
	@RequestMapping(value="/error.htm",method=RequestMethod.GET)
	public String testError(ModelMap modelMap) {
		logger.debug("Entered Controller : {} and method : {}", "Monitor", "testError");
		modelMap.addAttribute("error", "This is test error message!");
		return "error";
	}

}
