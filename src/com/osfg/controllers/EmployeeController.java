package com.osfg.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.osfg.model.Employee;

/**
 * 
 * @author athakur
 * Controller to handle employee information
 */
@Controller
public class EmployeeController {

	public static final String EMPLOYEE_FORM_PAGE = "employeeForm";
	public static final String COMMAND_NAME = "employeeDetails";

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@RequestMapping(value = "/getEmployeeInfoForm.htm", method = RequestMethod.GET)
	public String getEmployeeInfoForm(HttpServletRequest request, ModelMap modelMap) {
		logger.debug("Receive GET request for employee information");
		Employee empForm = new Employee();
		empForm.setName("Aniket Thakur");
		empForm.setAge(25);
		modelMap.addAttribute(COMMAND_NAME, empForm);
		modelMap.addAttribute("status","info");
		return EMPLOYEE_FORM_PAGE;
	}

	@RequestMapping(value = "/submitEmployeeInfoForm.htm", method = RequestMethod.POST)
	public String saveEmployeeInfo(@ModelAttribute Employee employeeDetails, Errors errors, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		logger.debug("Receive Employee Information Name : {} and Age : : {}", employeeDetails.getName(), employeeDetails.getAge());
		model.addAttribute(COMMAND_NAME, employeeDetails);
		if(employeeDetails.getName() == null || employeeDetails.getAge() <= 0) {
			model.addAttribute("status","failure");
		}
		else if(employeeDetails.getAge() <= 18) {
			model.addAttribute("status","warning");
		}
		else {
			model.addAttribute("status","success");
		}
		return EMPLOYEE_FORM_PAGE;
	}
	
	
	@RequestMapping(value = "/getEmployeeInfoData", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public Employee getEmployeeInfo(HttpServletRequest request) {
		logger.debug("Receive GET request for employee data information");
		Employee empForm = new Employee();
		empForm.setName("Aniket Thakur");
		empForm.setAge(25);
		return empForm;
	}

}
