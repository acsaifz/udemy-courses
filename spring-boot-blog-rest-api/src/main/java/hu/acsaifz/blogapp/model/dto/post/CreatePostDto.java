package hu.acsaifz.blogapp.model.dto.post;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatePostDto {
    @NotEmpty(message = "Post title can not be empty.")
    @Size(min = 2, message = "Post title should have at least 2 characters.")
    private String title;
    @NotEmpty(message = "Post description can not be empty.")
    @Size(min = 10, message = "Post description should have at least 10 characters.")
    private String description;
    @NotEmpty(message = "Post content can not be empty.")
    private String content;
}
