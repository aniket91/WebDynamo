package com.osfg.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 
 * @author athakur
 * Test class for Monitor
 */
@Controller
public class Monitor {
	
	Logger logger = LoggerFactory.getLogger(Monitor.class);
	
	@RequestMapping(value="/test.htm",method=RequestMethod.GET)
	public String test() {
		logger.debug("Entered Controller : {} and method : {}", "Monitor", "test");
		return "index";
	}

}
