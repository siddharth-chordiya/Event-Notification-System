package com.example.demo.service;

import com.example.demo.dto.EventRequest;
import com.example.demo.dto.EventResponse;

public interface NotificationInterface {

	public EventResponse notify(EventRequest request);
}
