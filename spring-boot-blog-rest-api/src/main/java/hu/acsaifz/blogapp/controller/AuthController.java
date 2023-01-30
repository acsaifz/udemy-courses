package hu.acsaifz.blogapp.controller;

import hu.acsaifz.blogapp.model.dto.auth.JwtTokenDto;
import hu.acsaifz.blogapp.model.dto.auth.LoginRequest;
import hu.acsaifz.blogapp.model.dto.auth.RegistrationRequest;
import hu.acsaifz.blogapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(
                authService.login(loginRequest),
                HttpStatus.OK
        );
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest){
        return new ResponseEntity<>(
                authService.register(registrationRequest),
                HttpStatus.CREATED
        );
    }
}
