package com.example.notification_service.service;

import com.example.notification_service.model.Notification;
import java.util.List;

public interface NotificationService {

    Notification sendNotification(Notification notification);

    List<Notification> getNotificationsByUser(Long userId);
    List<Notification> getAllNotifications();
}

