package com.example.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EventRequest;
import com.example.demo.service.NotificationInterface;

@RestController
@RequestMapping("/api")
public class Resources {

	private NotificationInterface notificationInterface;
	
	public Resources(NotificationInterface service) {
		this.notificationInterface = service;
	}
	
	
	@PostMapping(path = "/events", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<?> addNotification(@RequestBody EventRequest eventRequests) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(notificationInterface.notify(eventRequests), httpHeaders, HttpStatus.OK);
	}
	
}
