package com.project.HotelServer.ServicesAuth;

import com.project.HotelServer.Dto.SignUpRequest;
import com.project.HotelServer.Dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


public interface AuthService {
    Map<String, Object> CreateUser(SignUpRequest signUpRequest);
}
