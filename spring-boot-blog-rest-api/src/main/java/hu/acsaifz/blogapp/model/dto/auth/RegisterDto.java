package hu.acsaifz.blogapp.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
