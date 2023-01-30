package hu.acsaifz.blogapp.model.dto.post;

import hu.acsaifz.blogapp.model.dto.category.CategoryDto;
import hu.acsaifz.blogapp.model.dto.comment.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
    private CategoryDto category;
    private Set<CommentDto> comments;
}
