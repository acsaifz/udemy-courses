package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.dto.auth.JwtTokenDto;
import hu.acsaifz.blogapp.model.dto.auth.LoginDto;
import hu.acsaifz.blogapp.model.dto.auth.RegisterDto;

public interface AuthService {
    JwtTokenDto login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
