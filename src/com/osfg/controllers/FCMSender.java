package com.osfg.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.osfg.model.Employee;
import com.osfg.model.FCMConfig;
import com.osfg.utils.CommonUtils;
import com.osfg.utils.Constants;

@Controller
public class FCMSender {
	
	Logger logger = LoggerFactory.getLogger(FCMSender.class);
	
	@RequestMapping(value = "/getFcmInitForm.htm", method = RequestMethod.GET)
	public String getFcmInitPage(HttpServletRequest request, ModelMap modelMap) {
		logger.debug("Receive GET request for FCM init page");
		FCMConfig fcmConfig = new FCMConfig();
		modelMap.addAttribute("fcmConfig",fcmConfig);
		return "fcmInitPage";
	}
	
	@RequestMapping(value="/submitFcmNotification.htm", method = RequestMethod.POST)
	public String sendFcmNotification(@ModelAttribute FCMConfig fcmConfig, Errors errors, ModelMap model,
			HttpServletRequest request, HttpServletResponse response){
		
		if(fcmConfig ==null || fcmConfig.getServerApiKey() == null || fcmConfig.getServerApiKey().length() <1 || fcmConfig.getFmRegid() == null || fcmConfig.getFmRegid().length() < 1)
		{
			model.addAttribute("status","warning");
		}
		else {
			String res = sendFcmMessage(fcmConfig.getServerApiKey(), fcmConfig.getFmRegid());
			if(res.startsWith("SUCCESS")) {
				model.addAttribute("status","success");
			}
			else {
				model.addAttribute("status","failure");
			}
			model.addAttribute("output", res);
		}
		
		model.addAttribute("fcmConfig",fcmConfig);
		return "fcmInitPage";
		
	}
	
	private String sendFcmMessage(String serverApikey, String fcmRegId) {
		
        int responseCode = -1;
        String responseBody = null;
        boolean success  = false;
        try
        {
        	logger.debug("Sending FCM request for serverApikye :{}, fcmRegId : {}",serverApikey,fcmRegId );
        	byte[] postData = CommonUtils.getPostData(fcmRegId, true);
        	
            URL url = new URL(Constants.FCM_URL);
            HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();

            //set timeputs to 10 seconds
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
            httpURLConnection.setRequestProperty("Authorization", "key="+serverApikey);

            

            OutputStream out = httpURLConnection.getOutputStream();
            out.write(postData);
            out.close();
            responseCode = httpURLConnection.getResponseCode();
            //success
            if (responseCode == HttpStatus.SC_OK)
            {
                responseBody = CommonUtils.convertStreamToString(httpURLConnection.getInputStream());
                System.out.println("FCM message sent : " + responseBody);
                success = true;
                responseBody = "SUCCESS" + responseBody;
            }
            //failure
            else
            {
                responseBody = CommonUtils.convertStreamToString(httpURLConnection.getErrorStream());
                logger.error("Sending FCM request failed for regId: " + fcmRegId + " response: " + responseBody);
                responseBody = "FAILURE" + responseBody;
            }
            return responseBody;
        }
        catch (IOException ioe)
        {
        	logger.error("IO Exception in sending FCM request. regId: " + fcmRegId, ioe);
        	return ioe.getMessage();
        }
        catch (Exception e)
        {
        	logger.error("Unknown exception in sending FCM request. regId: " + fcmRegId);
        	return e.getMessage();
        }
		
	}
	
	

}
