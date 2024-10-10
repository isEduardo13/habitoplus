package com.habitoplus.habitoplusback.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.habitoplus.habitoplusback.Model.Notification;
import com.habitoplus.habitoplusback.Repository.NotificationRepository;



@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
   

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
    public Notification getNotificationById(Integer id) {
        return notificationRepository.findById(id).orElse(null);
    }
    
    public Notification addNotification(Notification notification) {
        System.out.println(notification.toString());
        return notificationRepository.save(notification);

    }
    public boolean updateNotification(Notification notification) {
        Notification existingNotification = notificationRepository.findById(notification.getId()).orElse(null);
        existingNotification.setProfile(notification.getProfile());
        existingNotification.setType(notification.getType());
        existingNotification.setDate(notification.getDate());
        existingNotification.setRead(notification.getRead());
        existingNotification.setMessage(notification.getMessage());
        return true;
    }
    public boolean deleteNotification(Integer id) {
        Notification existingNotification = notificationRepository.findById(id).orElse(null);
        if (existingNotification != null) {
            notificationRepository.delete(existingNotification);
            return true;
        }
        return false;
    }
    
}
