package com.osfg.model;

/**
 * 
 * @author athakur
 * Model object needed to make FCM call 
 * Can be extended later to take type of notification - data, notification etc
 */
public class FCMConfig {

	String serverApiKey;
	String fmRegid;
	
	public String getServerApiKey() {
		return serverApiKey;
	}
	public void setServerApiKey(String serverApiKey) {
		this.serverApiKey = serverApiKey;
	}
	public String getFmRegid() {
		return fmRegid;
	}
	public void setFmRegid(String fmRegid) {
		this.fmRegid = fmRegid;
	}
	
	
	
}
