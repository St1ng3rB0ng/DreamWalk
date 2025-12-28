package com.dreamwalk.auth.service.validation;

import com.dreamwalk.auth.dto.RegisterRequest;

public interface RegistrationValidationService {

    void validate(RegisterRequest request);
}
