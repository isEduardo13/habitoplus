package com.habitoplus.habitoplusback.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.Notification;
import com.habitoplus.habitoplusback.Repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

   
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(int id) {
        return notificationRepository.findById(id);
    }
    
    public Notification updateNotification(int id, Notification notificationDetails) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);

        if (optionalNotification.isPresent()) {
            Notification notificationToUpdate = optionalNotification.get();
            notificationToUpdate.setMessage(notificationDetails.getMessage());
            notificationToUpdate.setType(notificationDetails.getType());
            notificationToUpdate.setDate(notificationDetails.getDate());
            notificationToUpdate.setIsRead(notificationDetails.getIsRead());

            return notificationRepository.save(notificationToUpdate);
        } else {
            throw new RuntimeException("Notification not found with id: " + id);
        }
    }

    
    public void deleteNotification(int id) {
        notificationRepository.deleteById(id);
    }
    
}
