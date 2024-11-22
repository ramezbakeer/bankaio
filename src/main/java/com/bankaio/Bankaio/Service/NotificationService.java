package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.Notification;
import com.bankaio.Bankaio.Entity.User;
import com.bankaio.Bankaio.Entity.enums.NotificationStatus;
import com.bankaio.Bankaio.Model.NotificationDto;
import com.bankaio.Bankaio.Model.UserDto;
import com.bankaio.Bankaio.Repository.NotificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationService implements NotificationServiceInt {
    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;
    public NotificationService(NotificationRepository notificationRepository,ModelMapper modelMapper){
        this.notificationRepository=notificationRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public void sendNotification(UserDto userDto, String message) {
        Notification notification = new Notification();
        notification.setUser(modelMapper.map(userDto,User.class));
        notification.setMessage(message);
        notification.setStatus(NotificationStatus.UNREAD);
        modelMapper.map(notificationRepository.save(notification), NotificationDto.class);
    }

    @Override
    public List<NotificationDto> viewNotifications(Long userId) {
        return notificationRepository.findAllByUser_UserId(userId).stream().map((notification -> modelMapper.map(notification,NotificationDto.class))).toList();
    }

    @Override
    public void markAsRead(Long userId,Long notificationId) {
        Notification notification = notificationRepository.findByUser_UserIdAndNotificationId(userId,notificationId).orElseThrow(()->new RuntimeException("No notification Found"));
        notification.setStatus(NotificationStatus.READ);
        notificationRepository.save(notification);
    }
}
