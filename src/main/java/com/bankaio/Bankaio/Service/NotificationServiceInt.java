package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Model.NotificationDto;
import com.bankaio.Bankaio.Model.UserDto;

import java.util.List;

public interface NotificationServiceInt {
    void sendNotification(UserDto userDto, String message);
    List<NotificationDto> viewNotifications(Long userId);
    void markAsRead(Long userId,Long notificationId);
}
