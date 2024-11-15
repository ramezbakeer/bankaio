package com.bankaio.Bankaio.Model;

import java.util.Date;

public record UserDto(Long userId , String name, String email, Long phoneNumber,
                      String address, String role,
                      String password, Date createdAt, Date lastLogin) {
}

