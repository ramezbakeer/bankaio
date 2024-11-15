package com.bankaio.Bankaio.Repository;

import com.bankaio.Bankaio.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
