package com.example.demo.dto;

public class EventResponse {

	private String eventId;
	private String message;
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public EventResponse(String eventId, String message) {
		super();
		this.eventId = eventId;
		this.message = message;
	}
	public EventResponse() {
		super();
	}
	
	
}
