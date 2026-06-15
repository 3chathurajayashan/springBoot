package com.computerZone.pcshop.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    private String name;
    private String email;
    private String address;
    private String mobileNumber;
    private String password;
}
