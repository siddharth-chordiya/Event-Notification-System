package com.example.demo.service.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Map;

import com.example.demo.ValidEventPayload;
import com.example.demo.dto.EventRequest;

public class EventPayloadValidator implements ConstraintValidator<ValidEventPayload, EventRequest> {

    @Override
    public boolean isValid(EventRequest request, ConstraintValidatorContext context) {
        if (request.getEventType() == null || request.getPayload() == null) {
            return true;
        }

        Map<String, Object> payload = request.getPayload();
        boolean valid = true;

        context.disableDefaultConstraintViolation();

        switch (request.getEventType()) {
            case EMAIL :
                valid &= checkField(payload, "recipient", context);
                valid &= checkField(payload, "message", context);
                break;

            case SMS:
                valid &= checkField(payload, "phoneNumber", context);
                valid &= checkField(payload, "message", context);
                break;

            case PUSH:
                valid &= checkField(payload, "deviceId", context);
                valid &= checkField(payload, "message", context);
                break;
        }

        return valid;
    }

    private boolean checkField(Map<String, Object> payload, String field, ConstraintValidatorContext context) {
        if (!payload.containsKey(field) || payload.get(field) == null) {
            context.buildConstraintViolationWithTemplate("Missing required payload field: " + field)
                    .addPropertyNode("payload." + field)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
