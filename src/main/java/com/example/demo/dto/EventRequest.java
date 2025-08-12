package com.example.demo.dto;

import java.util.Map;

public class EventRequest {

    private EventType eventType;
    private Map<String, Object> payload;
    private String callbackUrl;
	
    public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public Map<String, Object> getPayload() {
		return payload;
	}
	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	
	public EventRequest(EventType eventType, Map<String, Object> payload, String callbackUrl) {
		super();
		this.eventType = eventType;
		this.payload = payload;
		this.callbackUrl = callbackUrl;
	}
	
	public EventRequest() {
		super();
	}
    
	
    
}
