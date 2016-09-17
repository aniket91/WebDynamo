package com.osfg.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		logger.debug("Receive GET request for emplyee information");
		Employee empForm = new Employee();
		empForm.setName("Aniket Thakur");
		empForm.setAge(25);
		modelMap.addAttribute(COMMAND_NAME, empForm);
		return EMPLOYEE_FORM_PAGE;
	}

	@RequestMapping(value = "/submitEmployeeInfoForm.htm", method = RequestMethod.POST)
	public void saveEmployeeInfo(@ModelAttribute Employee employeeDetails, Errors errors, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		logger.debug("Receive Employee Information Name : {} and Age : : {}", employeeDetails.getName(), employeeDetails.getAge());
	}

}
