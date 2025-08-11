package com.example.demo.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private EventType eventType;
    private Map<String, Object> payload;
    private String callbackUrl;
}
