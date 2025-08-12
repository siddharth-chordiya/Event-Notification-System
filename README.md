Run with Docker: 
` docker compose up --build `

API: POST http://localhost:8080/api/events

Example payloads: Email: {"eventType": "EMAIL","payload": { "recipient": "user@example.com", "message": "Welcome" },"callbackUrl": "http://localhost:8080/api/callback" }

SMS: {"eventType": "SMS","payload": { "phoneNumber": "+911234567890", "message": "OTP 1234" },"callbackUrl": "http://localhost:8080/api/callback" }

Push: {"eventType": "PUSH","payload": { "deviceId": "abc-123", "message": "Shipped" },"callbackUrl": "http://localhost:8080/api/callback" }


