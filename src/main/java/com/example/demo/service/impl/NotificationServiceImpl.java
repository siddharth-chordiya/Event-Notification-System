package com.example.demo.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Constants;
import com.example.demo.dto.Event;
import com.example.demo.dto.EventRequest;
import com.example.demo.dto.EventResponse;
import com.example.demo.service.NotificationInterface;
import com.example.demo.utils.CommonUtils;


@Service
public class NotificationServiceImpl implements NotificationInterface {
	
    private final ProcessorManager manager;

    public NotificationServiceImpl(ProcessorManager manager) {
        this.manager = manager;
    }

	@Override
	public EventResponse notify(EventRequest request) {
		
		EventResponse eventResponse = new EventResponse();
		String id = CommonUtils.getUniqueId();
		Event event = new Event(id, request.getEventType(), request.getPayload(), request.getCallbackUrl());
		manager.submit(event);
		
		eventResponse.setEventId(id);
		eventResponse.setMessage(Constants.successResponseMsg);
		
 		return eventResponse;
	}
}
