package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.dto.auth.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
