package hu.acsaifz.blogapp.model.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateCommentDto {
    private String name;
    private String email;
    private String body;
}
