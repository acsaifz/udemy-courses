package hu.acsaifz.blogapp.model.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryCreateRequest {
    private String name;
    private String description;
}
