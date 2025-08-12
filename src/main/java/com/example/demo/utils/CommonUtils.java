package com.example.demo.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {
	
	private CommonUtils() {
		// Utility class restricted from being constructed
	}

	public static String getUniqueId() {
		return UUID.randomUUID().toString();
	}
}
