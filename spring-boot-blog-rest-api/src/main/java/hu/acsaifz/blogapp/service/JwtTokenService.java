package hu.acsaifz.blogapp.service;

import org.springframework.security.core.Authentication;

public interface JwtTokenService {
    String generateToken(Authentication authentication);

    String getUsername(String token);
}