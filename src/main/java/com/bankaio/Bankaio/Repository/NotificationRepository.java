package com.bankaio.Bankaio.Repository;

import com.bankaio.Bankaio.Entity.Bill;
import com.bankaio.Bankaio.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findAllByUser_UserId(Long userId);
    Optional<Notification> findByUser_UserIdAndNotificationId(Long userId, Long notificationId);
}
