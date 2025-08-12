package com.example.demo.service.impl;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

import com.example.demo.dto.Constants;
import com.example.demo.dto.Event;
import com.example.demo.dto.EventStatus;

public class EventProcessor implements Runnable {

    private final BlockingQueue<Event> queue;
    private final long processingMillis;
    private final CallbackClient callbackClient;
    private final Random random = new Random();

    public EventProcessor(BlockingQueue<Event> queue, long processingMillis, CallbackClient callbackClient) {
        this.queue = queue;
        this.processingMillis = processingMillis;
        this.callbackClient = callbackClient;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Event e = queue.take();
                e.setStatus(EventStatus.PROCESSING);
                Thread.sleep(processingMillis);
                System.out.println("started processing for : "+ e.getEventId());

                // Simulate 10% failure
                boolean fail = random.nextInt(100) < 10;
                
                if (fail) {
                    e.setStatus(EventStatus.FAILED);
                    e.setErrorMessage(Constants.errorResponseMsg);
                } else {
                    e.setStatus(EventStatus.COMPLETED);
                }
                e.setProcessedAt(Instant.now());
                callbackClient.sendCallback(e).subscribe();
            } 
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
