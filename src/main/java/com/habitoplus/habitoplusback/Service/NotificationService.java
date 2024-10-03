package com.habitoplus.habitoplusback.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.Notification;
import com.habitoplus.habitoplusback.Repository.NotificationRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }
    public Notification getById(int id) {
        return notificationRepository.findById(id).get();
    }
    public void delete(int id) {
        notificationRepository.deleteById(id);
    }


}
