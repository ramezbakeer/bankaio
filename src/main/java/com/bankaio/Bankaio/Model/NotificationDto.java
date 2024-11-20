package com.bankaio.Bankaio.Model;

import com.bankaio.Bankaio.Entity.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private Long notificationId;
    private String message;
    private NotificationStatus status;
    private LocalDateTime date;
    private UserDto userDto;
}
