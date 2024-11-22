package com.bankaio.Bankaio.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String username;
    private String email;
    private Long phoneNumber;
    private String address;
    private String role;// "Customer" or "Admin"
    private String password;
}
