package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);

    User saveUser(User user);
}
