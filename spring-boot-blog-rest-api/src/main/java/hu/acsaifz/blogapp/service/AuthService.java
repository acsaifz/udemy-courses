package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.dto.auth.LoginDto;
import hu.acsaifz.blogapp.model.dto.auth.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
