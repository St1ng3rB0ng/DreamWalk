package com.dreamwalk.auth.service;

import com.dreamwalk.auth.dto.AuthResponse;
import com.dreamwalk.auth.dto.LoginRequest;
import com.dreamwalk.auth.dto.RegisterRequest;
import com.dreamwalk.auth.entity.Role;
import com.dreamwalk.auth.entity.User;
import com.dreamwalk.auth.entity.UserStatus;
import com.dreamwalk.auth.repository.UserRepository;
import com.dreamwalk.auth.security.jwt.JwtService;
import com.dreamwalk.auth.service.validation.RegistrationValidationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RegistrationValidationService registrationValidationService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, RegistrationValidationService registrationValidationService)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.registrationValidationService = registrationValidationService;
    }

    public void register(RegisterRequest request)
    {

        registrationValidationService.validate(request);

        User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()), Role.USER);

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request)
    {

        User user = userRepository.findByEmailAndStatus(request.getEmail(), UserStatus.ACTIVE)
                                  .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}
