package com.project.HotelServer.Controller.auth;

import com.project.HotelServer.ServicesAuth.AuthServiceImpl;
import com.project.HotelServer.Dto.AuthenticationRequest;
import com.project.HotelServer.Dto.SignUpRequest;
import com.project.HotelServer.Dto.AuthenticationResponse;
import com.project.HotelServer.Entity.User;
import com.project.HotelServer.Repository.UserRepository;
import com.project.HotelServer.ServicesAuth.Jwt.UserService;
import com.project.HotelServer.Util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
    private final AuthServiceImpl authServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthServiceImpl authServiceImpl, AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtil jwtUtil, UserService userService) {
        this.authServiceImpl = authServiceImpl;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
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


    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            errorResponse.put("message", "Incorrect username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }

        return ResponseEntity.ok(authenticationResponse);
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
