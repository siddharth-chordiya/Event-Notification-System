package com.example.demo.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import com.example.demo.dto.Event;
import com.example.demo.dto.EventType;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class ProcessorManager {

    private final Map<EventType, LinkedBlockingQueue<Event>> queues = new EnumMap<>(EventType.class);
    private final Map<EventType, ExecutorService> executors = new EnumMap<>(EventType.class);
    private final CallbackClient callbackClient;
    private volatile boolean accepting = true;

    public ProcessorManager(CallbackClient callbackClient) {
        this.callbackClient = callbackClient;
        for (EventType t : EventType.values()) {
            queues.put(t, new LinkedBlockingQueue<>());
        }
    }

    @PostConstruct
    public void startProcessors() {
        executors.put(EventType.EMAIL, Executors.newSingleThreadExecutor(r -> new Thread(r, "processor-email")));
        executors.put(EventType.SMS, Executors.newSingleThreadExecutor(r -> new Thread(r, "processor-sms")));
        executors.put(EventType.PUSH, Executors.newSingleThreadExecutor(r -> new Thread(r, "processor-push")));

        executors.get(EventType.EMAIL).submit(new EventProcessor(queues.get(EventType.EMAIL), 5000, callbackClient));
        executors.get(EventType.SMS).submit(new EventProcessor(queues.get(EventType.SMS), 3000, callbackClient));
        executors.get(EventType.PUSH).submit(new EventProcessor(queues.get(EventType.PUSH), 2000, callbackClient));
    }

    public void submit(Event event) {
        if (!accepting) throw new RejectedExecutionException("Shutting down - not accepting events");
        LinkedBlockingQueue<Event> q = queues.get(event.getEventType());
        System.out.println(event.getEventId());
        q.offer(event);
    }

    @PreDestroy
    public void shutdown() {
        accepting = false;
        for (Map.Entry<EventType, LinkedBlockingQueue<Event>> e : queues.entrySet()) {
            try {
                LinkedBlockingQueue<Event> q = e.getValue();
                while (!q.isEmpty()) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        for (ExecutorService exec : executors.values()) {
            exec.shutdown();
            try {
                if (!exec.awaitTermination(10, TimeUnit.SECONDS)) {
                    exec.shutdownNow();
                }
            } catch (InterruptedException ex) {
                exec.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}
