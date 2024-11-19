package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Model.NotificationDto;

import java.util.List;

public interface NotificationServiceInt {
    public NotificationDto sendNotification(Long userId,String message);
    public List<NotificationDto> viewNotifications(Long userId);
    public void markAsRead(Long notificationId);
}
