package com.osfg.controllers;

import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.osfg.model.XmlForm;

@Controller
public class XMLBeautifier {
	
	public static final String COMMAND_NAME = "xmlForm";
	
	Logger logger = LoggerFactory.getLogger(XMLBeautifier.class);
	
	@RequestMapping(value="/beautifyXml.htm",method={RequestMethod.GET, RequestMethod.POST})
	public String beautifyXml(HttpServletRequest request, ModelMap modelMap, XmlForm xmlForm) {
		
		if(RequestMethod.GET.name().equalsIgnoreCase(request.getMethod())) {
			modelMap.addAttribute(COMMAND_NAME, new XmlForm(""));
		}
		else if (RequestMethod.POST.name().equalsIgnoreCase(request.getMethod())) {
			
			String xml = xmlForm.getXml();
			
			Transformer transformer;
			try {
				transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				//initialize StreamResult with File object to save to file
				StreamResult result = new StreamResult(new StringWriter());
				transformer.transform(new StreamSource(new StringReader(xml)), result);
				String xmlString = result.getWriter().toString();
				modelMap.addAttribute(COMMAND_NAME, new XmlForm(xmlString));
			} catch (TransformerFactoryConfigurationError | TransformerException e) {
				logger.error("Error parsing XML input", e);
				modelMap.addAttribute("error", "Error parsing XML input" + e.getMessage());
			}
			
			
		}
		else {
			modelMap.addAttribute("error", "Method not Allowed!");
			return "error";
		}
		
		return "xmlForm";
	}

	
}
