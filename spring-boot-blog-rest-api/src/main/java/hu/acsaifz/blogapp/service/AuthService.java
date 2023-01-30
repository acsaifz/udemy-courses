package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.dto.auth.JwtTokenDto;
import hu.acsaifz.blogapp.model.dto.auth.LoginRequest;
import hu.acsaifz.blogapp.model.dto.auth.RegistrationRequest;

public interface AuthService {
    JwtTokenDto login(LoginRequest loginRequest);

    String register(RegistrationRequest registrationRequest);
}
