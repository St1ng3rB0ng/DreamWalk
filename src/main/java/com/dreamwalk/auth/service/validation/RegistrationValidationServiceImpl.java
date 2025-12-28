package com.dreamwalk.auth.service.validation;

import com.dreamwalk.auth.dto.RegisterRequest;
import com.dreamwalk.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationValidationServiceImpl implements
                RegistrationValidationService{

    private final UserRepository userRepository;

    public RegistrationValidationServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(RegisterRequest request)
    {

        validateEmail(request.getEmail());
        validateUsername(request.getUsername());
        validatePassword(request.getPassword());
    }

    private void validateEmail(String email)
    {

        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email is required");
        }

        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            throw new IllegalArgumentException("Invalid email format");
        }

        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("This email is already registered");
        }
    }

    private void validateUsername(String username)
    {

        if(username == null || username.isBlank()){
            throw new IllegalArgumentException("Username is required");
        }

        if(!username.matches("^[a-zA-Z0-9_]{3,20}$")){
            throw new IllegalArgumentException("Username must be 3-20 characters and contain only letters, numbers or underscore");
        }

        if(userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("Username already exists");
        }
    }

    private void validatePassword(String password)
    {

        if(password == null || password.isBlank()){
            throw new IllegalArgumentException("Password is required");
        }

        if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")){
            throw new IllegalArgumentException("Password must contain at least 8 characters, one uppercase letter and one number");
        }
    }
}
