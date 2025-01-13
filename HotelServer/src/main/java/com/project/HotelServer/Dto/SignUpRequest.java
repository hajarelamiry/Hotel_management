package com.project.HotelServer.Dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    private String password;
    private String name;
}
