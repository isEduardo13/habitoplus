package com.habitoplus.habitoplusback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.habitoplus.habitoplusback.model.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByProfile_IdProfile(int idProfile);
    void deleteByIdAndProfile_IdProfile(int idNotification, int idProfile);
}