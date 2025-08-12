package com.example.demo.dto;

import java.util.Map;

import com.example.demo.ValidEventPayload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@ValidEventPayload
public class EventRequest {

	@NotNull
    private EventType eventType;
    
    @NotNull
    private Map<String, Object> payload;
    
    @NotBlank
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
