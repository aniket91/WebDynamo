package com.osfg.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class CommonUtils {
	
    public static byte[] getPostData(String fcmRegId, boolean sendMessage) throws JSONException {
        HashMap<String, String> payLoadMap = new HashMap<>();
        JSONObject payloadObject = new JSONObject();
        JSONObject data;

        if(sendMessage) {
            payLoadMap.put(Constants.PAYLOAD_DATA_MSG_TITLE, "Welcome!");
            payLoadMap.put(Constants.PAYLOAD_DATA_MSG_BODY, "Greetings from Server!");
        }


        //insert payload data
        if (payLoadMap != null && payLoadMap.size() > 0)
        {
            data = new JSONObject(payLoadMap);
            payloadObject.put(Constants.PAYLOAD_DATA, data);
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
