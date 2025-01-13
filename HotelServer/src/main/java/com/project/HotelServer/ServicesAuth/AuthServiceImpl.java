package com.project.HotelServer.ServicesAuth;


import com.project.HotelServer.Dto.SignUpRequest;
import com.project.HotelServer.Dto.UserDto;
import com.project.HotelServer.Entity.User;
import com.project.HotelServer.Enums.UserRole;
import com.project.HotelServer.Repository.UserRepository;
import com.project.HotelServer.Util.JwtUtil;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service

public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PostConstruct
    public void creatAnAdminAccount(){
        Optional<User> adminAccount=userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin account created successfully");

        }else{
            System.out.println("Admin account already exist");
        }

    }


    public Map<String, Object> CreateUser(SignUpRequest signUpRequest) {
        if (userRepository.findFirstByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new EntityExistsException("User already exists with email " + signUpRequest.getEmail());
        }

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));

        User savedUser = userRepository.save(user);

        String token = jwtUtil.generateToken(savedUser);

        Map<String, Object> response = new HashMap<>();
        response.put("id", savedUser.getId());
        response.put("name", savedUser.getName());
        response.put("jwt", token);

        return response;
    }


    public AuthenticationResponse authenticate(User request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                       request.getUsername(),
                       request.getPassword()
                )
        );
        User user=userRepository.findFirstByEmail(request.getUsername()).orElseThrow();
        String token=jwtUtil.generateToken(user);

        return new AuthenticationResponse(token);
    }

}
