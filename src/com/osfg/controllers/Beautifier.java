package com.osfg.controllers;

import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.osfg.model.EntityType;
import com.osfg.model.UnformattedStringForm;

/**
 * 
 * @author athakur
 * Controller class used for beautifier workflows
 */
@Controller
public class Beautifier {
	
	public static final String COMMAND_NAME = "unformattedStringForm";
	public static final String PAGE = "unformattedStringLayout";
	public static final String ENTITY_TYPE = "ENTITY_TYPE";
	
	Logger logger = LoggerFactory.getLogger(Beautifier.class);
	
	private static final JsonParser parser = new JsonParser();
	private static  final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@RequestMapping(value="/beautifyXml.htm",method={RequestMethod.GET, RequestMethod.POST})
	public String beautifyXml(HttpServletRequest request, ModelMap modelMap, UnformattedStringForm unformattedStringForm) {
		
		if(RequestMethod.GET.name().equalsIgnoreCase(request.getMethod())) {
			modelMap.addAttribute(COMMAND_NAME, new UnformattedStringForm(""));
		}
		else if (RequestMethod.POST.name().equalsIgnoreCase(request.getMethod())) {
			
			String xml = unformattedStringForm.getUnformattedString();
			
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
				modelMap.addAttribute(COMMAND_NAME, new UnformattedStringForm(xmlString));
			} catch (TransformerFactoryConfigurationError | TransformerException e) {
				logger.error("Error parsing XML input", e);
				modelMap.addAttribute("error", "Error parsing XML input" + e.getMessage());
			}	
			
		}
		else {
			modelMap.addAttribute("error", "Method not Allowed!");
			return "error";
		}
		
		modelMap.addAttribute(ENTITY_TYPE, EntityType.XML);
		return PAGE;
	}
	
	@RequestMapping(value="/beautifyJson.htm",method={RequestMethod.GET, RequestMethod.POST})
	public String beautifyJson(HttpServletRequest request, ModelMap modelMap, UnformattedStringForm unformattedStringForm) {
		
		if(RequestMethod.GET.name().equalsIgnoreCase(request.getMethod())) {
			modelMap.addAttribute(COMMAND_NAME, new UnformattedStringForm(""));
		}
		else if (RequestMethod.POST.name().equalsIgnoreCase(request.getMethod())) {
			try {
				String jsonString = unformattedStringForm.getUnformattedString();
				JsonElement el = parser.parse(jsonString);
				modelMap.addAttribute(COMMAND_NAME, new UnformattedStringForm(gson.toJson(el)));
			}
			catch(JsonSyntaxException e) {
				logger.error("Error parsing JSON input", e);
				modelMap.addAttribute("error", "Error parsing XML input" + e.getMessage());
			}
			
			
		}
		else {
			modelMap.addAttribute("error", "Method not Allowed!");
			return "error";
		}
		
		modelMap.addAttribute(ENTITY_TYPE, EntityType.JSON);
		return PAGE;
	}

	
}
