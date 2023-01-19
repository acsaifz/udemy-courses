package hu.acsaifz.blogapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatePostDto {
    private String title;
    private String description;
    private String content;
}
