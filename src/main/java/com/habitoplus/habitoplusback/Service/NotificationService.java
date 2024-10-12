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

    // Método para agregar una nueva notificación
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Método para consultar todas las notificaciones
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Método para consultar una notificación por ID
    public Optional<Notification> getNotificationById(int id) {
        return notificationRepository.findById(id);
    }

    // Método para actualizar una notificación existente
    public Notification updateNotification(int id, Notification notificationDetails) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);

        if (optionalNotification.isPresent()) {
            Notification notificationToUpdate = optionalNotification.get();
            notificationToUpdate.setMessage(notificationDetails.getMessage());
            notificationToUpdate.setType(notificationDetails.getType());
            notificationToUpdate.setDate(notificationDetails.getDate());
            notificationToUpdate.setRead(notificationDetails.getRead());

            return notificationRepository.save(notificationToUpdate);
        } else {
            throw new RuntimeException("Notification not found with id: " + id);
        }
    }

    // Método para eliminar una notificación por ID
    public void deleteNotificationById(int id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notification not found with id: " + id);
        }
    }
}
