package com.dreamwalk.auth.security.jwt;

import com.dreamwalk.auth.entity.User;

public interface JwtService {

    String generateToken(User user);

    boolean isTokenValid(String token);

    String extractEmail(String token);
}
