package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.Category;
import hu.acsaifz.blogapp.model.dto.category.CategoryDto;
import hu.acsaifz.blogapp.model.dto.category.CreateCategoryDto;
import hu.acsaifz.blogapp.model.dto.category.UpdateCategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CreateCategoryDto createCategoryDto);

    CategoryDto getCategoryById(long id);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(UpdateCategoryDto updateCategoryDto, long id);

    void deleteCategoryById(long id);

    Category findCategoryById(long id);

    boolean existsCategoryById(long id);
}
