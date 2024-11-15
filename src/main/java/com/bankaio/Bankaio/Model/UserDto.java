package com.bankaio.Bankaio.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String name;
    private String email;
    private Long phoneNumber;
    private String address;
    private String role;// "Customer" or "Admin"
    private String password;
    private Date createdAt;
    private Date lastLogin;
}

