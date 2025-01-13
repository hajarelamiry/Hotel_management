package com.project.HotelServer.Controller.auth;

import com.project.HotelServer.Dto.SignUpRequest;
import com.project.HotelServer.Dto.UserDto;
import com.project.HotelServer.ServicesAuth.AuthService;
import com.project.HotelServer.ServicesAuth.AuthServiceImpl;
import com.project.HotelServer.ServicesAuth.AuthenticationResponse;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
   private final AuthServiceImpl authServiceImpl;

    public AuthController(AuthServiceImpl authServiceImpl){
        this.authServiceImpl=authServiceImpl;
    }
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signUpUser(@RequestBody SignUpRequest request) {
        try {
            Map<String, Object> response = authServiceImpl.CreateUser(request);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (EntityExistsException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "User already exists");
            errorResponse.put("details", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
    }


    /*private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signUpUser(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authService.CreateUser(request));

    }
    */

}
