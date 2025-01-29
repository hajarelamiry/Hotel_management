package com.project.HotelServer.Dto;

import com.project.HotelServer.Enums.UserRole;




public class AuthenticationResponse {

    private String jwt;
    private Long userId;
    private UserRole userRole;

    public String getJwt() {
        return jwt;
    }

    public Long getUserId() {
        return userId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
