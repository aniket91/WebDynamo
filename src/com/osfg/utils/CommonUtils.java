package com.osfg.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author athakur
 *
 */
public class CommonUtils {
	
	/**
	 * If you want to send notification payload i.e dont want to handle the notification yourself
	 * in the app like in case of data payload just turn on this flag
	 */
	public static boolean sendStandAloneNotification = false;
	
	
	/**
	 * 
	 * @param fcmRegId device registered fcm reg Id
	 * @param sendMessage whether you want to send a message data payload for just empty fcm notification
	 * @return
	 * @throws JSONException
	 */
    public static byte[] getPostData(String fcmRegId, boolean sendMessage) throws JSONException {
        HashMap<String, String> payLoadMap = new HashMap<>();
        HashMap<String, String> notificationPayLoadMap = new HashMap<>();
        JSONObject payloadObject = new JSONObject();
        JSONObject data;
        JSONObject notification;
        
        if(sendStandAloneNotification) { 	 
        	 notificationPayLoadMap.put("title", "Welcome again!");
        	 notificationPayLoadMap.put("body", "Notification from server!");
        	 notificationPayLoadMap.put("icon", "ic_launcher");
        	
        }
        else if(sendMessage) {
            payLoadMap.put(Constants.PAYLOAD_DATA_MSG_TITLE, "Welcome!");
            payLoadMap.put(Constants.PAYLOAD_DATA_MSG_BODY, "Greetings from Server!");
        }


        //insert payload data
        if (payLoadMap != null && payLoadMap.size() > 0)
        {
            data = new JSONObject(payLoadMap);
            payloadObject.put(Constants.PAYLOAD_DATA, data);
        }
        if (notificationPayLoadMap != null && notificationPayLoadMap.size() > 0)
        {
        	notification = new JSONObject(notificationPayLoadMap);
            payloadObject.put(Constants.PAYLOAD_NOTIFICATION, notification);
        }

        payloadObject.put(Constants.TO, fcmRegId);

        return payloadObject.toString().getBytes();
    }
    
    public static String convertStreamToString (InputStream inStream) throws Exception
    {
        InputStreamReader inputStream = new InputStreamReader(inStream);
        BufferedReader bReader = new BufferedReader(inputStream);

        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line = bReader.readLine()) != null)
        {
            sb.append(line);
        }

        return sb.toString();
    }

}
