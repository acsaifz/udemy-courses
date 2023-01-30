package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.Category;
import hu.acsaifz.blogapp.model.dto.category.CategoryCreateRequest;
import hu.acsaifz.blogapp.model.dto.category.CategoryDto;
import hu.acsaifz.blogapp.model.dto.category.CategoryUpdateRequest;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryCreateRequest categoryCreateRequest);

    CategoryDto getCategoryById(long id);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategoryById(CategoryUpdateRequest categoryUpdateRequest, long id);

    void deleteCategoryById(long id);

    Category findCategoryById(long id);

    boolean existsCategoryById(long id);
}
