package com.example.demo.service.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.Event;
import com.example.demo.dto.EventStatus;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class CallbackClient {

    private final WebClient webClient;

    public CallbackClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public Mono<Void> sendCallback(Event e) {
        Map<String, Object> body = new HashMap<>();
        body.put("eventId", e.getEventId());
        body.put("eventType", e.getEventType().name());
        body.put("status", e.getStatus().name());
        if (e.getStatus() == EventStatus.FAILED) {
            body.put("errorMessage", e.getErrorMessage());
        }
        body.put("processedAt", e.getProcessedAt().toString());
        System.out.println("calling callbackurl for : "+ body+" callback url : "+e.getCallbackUrl());

        return webClient.post()
                .uri(e.getCallbackUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .toEntity(String.class)
                .doOnSuccess(response -> {
                    System.out.println("Callback success: HTTP " + response.getStatusCode());
                    System.out.println("Response body: " + response.getBody());
                })
                .doOnError(err -> {
                    System.err.println("Callback failed for event " + e.getEventId() + ": " + err.getMessage());
                })
                .onErrorResume(ex -> Mono.empty())
                .then();
    }
}
