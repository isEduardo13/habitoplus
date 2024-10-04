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
    public Notification getNotificationById(int id) {
        return notificationRepository.findById(id).orElse(null);
    }
    public Notification getNotificationByMensaje(String mensaje) {
      return notificationRepository.findByMensaje(mensaje);
    }
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
    public boolean updateNotification(Notification notification) {
        Notification existingNotification = notificationRepository.findById(notification.getId()).orElse(null);
        existingNotification.setDiasConsecutivos(notification.getDiasConsecutivos());
        existingNotification.setFechaInicio(notification.getFechaInicio());
        existingNotification.setFechaFin(notification.getFechaFin());
        existingNotification.setMensaje(notification.getMensaje());
        return true;
    }
    public boolean deleteNotification(int id) {
        Notification existingNotification = notificationRepository.findById(id).orElse(null);
        if (existingNotification != null) {
            notificationRepository.delete(existingNotification);
            return true;
        }
        return false;
    }
    
}
