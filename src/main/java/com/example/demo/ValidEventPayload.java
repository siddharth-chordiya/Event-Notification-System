package com.example.demo;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

import com.example.demo.service.impl.EventPayloadValidator;

@Target({ ElementType.TYPE }) // Class-level validation
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EventPayloadValidator.class)
@Documented
public @interface ValidEventPayload {
    String message() default "Invalid payload for given eventType";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
