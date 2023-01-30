package hu.acsaifz.blogapp.model.dto.comment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentUpdateRequest {
    @NotEmpty(message = "Name can not be empty")
    private String name;
    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Email must be well formed.")
    private String email;
    @NotEmpty(message = "Body can not be empty")
    @Size(min = 10, message = "Comment body should have at least 10 characters.")
    private String body;
}
