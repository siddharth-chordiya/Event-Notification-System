package com.example.demo.dto;

import java.time.Instant;
import java.util.Map;

public class Event {
    private final String eventId;
    private final EventType eventType;
    private final Map<String,Object> payload;
    private final String callbackUrl;
    private volatile EventStatus status;
    private Instant createdAt;
    private Instant processedAt;
    private String errorMessage;

    public Event(String eventId, EventType eventType, Map<String,Object> payload, String callbackUrl) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.payload = payload;
        this.callbackUrl = callbackUrl;
        this.status = EventStatus.ACCEPTED;
        this.createdAt = Instant.now();
    }

    public String getEventId() { return eventId; }
    public EventType getEventType() { return eventType; }
    public Map<String, Object> getPayload() { return payload; }
    public String getCallbackUrl() { return callbackUrl; }
    public EventStatus getStatus() { return status; }
    public void setStatus(EventStatus status) { this.status = status; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getProcessedAt() { return processedAt; }
    public void setProcessedAt(Instant processedAt) { this.processedAt = processedAt; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
