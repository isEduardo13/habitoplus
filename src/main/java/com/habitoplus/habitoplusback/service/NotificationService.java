package com.habitoplus.habitoplusback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.dto.NotificationDTO;
import com.habitoplus.habitoplusback.model.Notification;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ProfileService profileService;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public List<Notification> getNotificationsByProfileId(int profileId) {
        Profile profile = profileService.getProfileById(profileId);
        return profile.getNotifications();
    }

    public void deleteNotification(int idNotificationId) {
        notificationRepository.deleteById(idNotificationId);
    }

    public void addNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setMessage(notificationDTO.getMessage());
        notification.setType(notificationDTO.getType());
        notification.setDate(notificationDTO.getDate());
        notification.setIsRead(notificationDTO.getIsRead());
        Profile profile = profileService.getProfileById(notificationDTO.getIdProfile());
        notification.setProfile(profile);
        notificationRepository.save(notification);
    }
}
