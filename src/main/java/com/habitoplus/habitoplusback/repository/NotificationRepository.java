package com.habitoplus.habitoplusback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.habitoplus.habitoplusback.model.Notification;



@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
  
}
