package com.wizeline.factory;


import com.wizeline.notifications.EmailNotification;
import com.wizeline.notifications.Notification;
import com.wizeline.notifications.PushNotification;
import com.wizeline.notifications.SMSNotification;

public class NotificationFactory {

    public Notification createNotification(String channel) {
        if (channel == null || channel.isEmpty())
            return null;
        switch (channel) {
            case "SMS":
                return new SMSNotification();
            case "EMAIL":
                return new EmailNotification();
            case "PUSH":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown channel " + channel);
        }
    }
}
