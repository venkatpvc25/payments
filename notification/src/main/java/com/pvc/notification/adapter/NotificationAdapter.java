package com.pvc.notification.adapter;

import org.springframework.stereotype.Component;

import com.pvc.core.port.NotificationPort;

@Component
public class NotificationAdapter implements NotificationPort {

    @Override
    public void notifyUser(String userId, String message) {
        System.out.println("Notifying user " + userId + " with message: " + message);
    }

}
