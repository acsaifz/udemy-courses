package hu.acsaifz.blogapp.service.impl;

import hu.acsaifz.blogapp.exception.BlogApiException;
import hu.acsaifz.blogapp.model.User;
import hu.acsaifz.blogapp.model.dto.auth.JwtTokenDto;
import hu.acsaifz.blogapp.model.dto.auth.LoginRequest;
import hu.acsaifz.blogapp.model.dto.auth.RegistrationRequest;
import hu.acsaifz.blogapp.service.AuthService;
import hu.acsaifz.blogapp.service.JwtTokenService;
import hu.acsaifz.blogapp.service.RoleService;
import hu.acsaifz.blogapp.service.UserService;
import hu.acsaifz.blogapp.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtTokenService jwtTokenService;

    @Override
    public JwtTokenDto login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new JwtTokenDto(jwtTokenService.generateToken(authentication));
    }

    @Override
    public String register(RegistrationRequest registrationRequest) {
        if(userService.existsUserByUsername(registrationRequest.getUsername())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username is already exists.");
        }

        if (userService.existsUserByEmail(registrationRequest.getEmail())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Email is already exists.");
        }

        User user = userMapper.toUser(registrationRequest);
        user.addRole(roleService.findRoleByName("ROLE_USER"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.saveUser(user);

        return "User has registered successfully";
    }
}
