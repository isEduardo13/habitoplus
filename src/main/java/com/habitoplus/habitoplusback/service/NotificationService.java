package com.habitoplus.habitoplusback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.model.Notification;
import com.habitoplus.habitoplusback.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotificationsByProfileId(int profileId) {
        return notificationRepository.findByProfile_IdProfile(profileId);
    }

    public void deleteNotificationByProfile(int notificationId, int profileId) {
        notificationRepository.deleteByIdAndProfile_IdProfile(notificationId , profileId);
    }

    public void addNotification(Notification notification) {
        notificationRepository.save(notification);
    }
}
