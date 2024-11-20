package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.Notification;
import com.bankaio.Bankaio.Entity.User;
import com.bankaio.Bankaio.Entity.enums.NotificationStatus;
import com.bankaio.Bankaio.Model.NotificationDto;
import com.bankaio.Bankaio.Repository.NotificationRepository;
import org.modelmapper.ModelMapper;

import java.util.List;

public class NotificationService implements NotificationServiceInt {
    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private NotificationService(NotificationRepository notificationRepository,UserService userService,ModelMapper modelMapper){
        this.notificationRepository=notificationRepository;
        this.modelMapper=modelMapper;
        this.userService=userService;
    }
    @Override
    public NotificationDto sendNotification(NotificationDto notificationDto) {
        notificationDto.setStatus(NotificationStatus.UNREAD);
        return modelMapper.map(notificationRepository.save(modelMapper.map(notificationDto, Notification.class)),NotificationDto.class);
    }

    @Override
    public List<NotificationDto> viewNotifications(Long userId) {
        return userService.viewProfile(userId).getNotificationList();
    }

    @Override
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(()-> new RuntimeException("NO Notification Found"));
        notification.setStatus(NotificationStatus.READ);
        notificationRepository.save(notification);
    }
}
