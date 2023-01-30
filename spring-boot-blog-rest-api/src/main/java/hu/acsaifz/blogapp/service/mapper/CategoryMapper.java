package hu.acsaifz.blogapp.service.mapper;

import hu.acsaifz.blogapp.model.Category;
import hu.acsaifz.blogapp.model.dto.category.CategoryDto;
import hu.acsaifz.blogapp.model.dto.category.CategoryCreateRequest;
import hu.acsaifz.blogapp.model.dto.category.CategoryUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "posts", ignore = true)
    Category toCategory(CategoryCreateRequest dto);

    CategoryDto toDto(Category category);

    List<CategoryDto> toDto(List<Category> categories);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "posts", ignore = true)
    void updateCategoryFromDto(CategoryUpdateRequest dto, @MappingTarget Category category);
}
