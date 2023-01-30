package hu.acsaifz.blogapp.service.impl;

import hu.acsaifz.blogapp.exception.BlogApiException;
import hu.acsaifz.blogapp.exception.ResourceNotFoundException;
import hu.acsaifz.blogapp.model.Category;
import hu.acsaifz.blogapp.model.dto.category.CategoryCreateRequest;
import hu.acsaifz.blogapp.model.dto.category.CategoryDto;
import hu.acsaifz.blogapp.model.dto.category.CategoryUpdateRequest;
import hu.acsaifz.blogapp.repository.CategoryRepository;
import hu.acsaifz.blogapp.service.CategoryService;
import hu.acsaifz.blogapp.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryCreateRequest categoryCreateRequest) {
        if (categoryRepository.existsCategoryByName(categoryCreateRequest.getName())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Category name is already exists.");
        }

        Category result = categoryRepository.save(
                categoryMapper.toCategory(categoryCreateRequest)
        );
        return categoryMapper.toDto(result);
    }

    @Override
    public CategoryDto getCategoryById(long id) {
        Category result = findCategoryById(id);
        return categoryMapper.toDto(result);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> result = categoryRepository.findAll();
        return categoryMapper.toDto(result);
    }

    @Override
    public CategoryDto updateCategoryById(CategoryUpdateRequest categoryUpdateRequest, long id) {
        Category result = findCategoryById(id);
        categoryMapper.updateCategoryFromDto(categoryUpdateRequest, result);
        result = categoryRepository.save(result);
        return categoryMapper.toDto(result);
    }

    @Override
    public void deleteCategoryById(long id) {
        Category result = findCategoryById(id);
        categoryRepository.delete(result);
    }

    @Override
    public Category findCategoryById(long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }

    @Override
    public boolean existsCategoryById(long id) {
        return categoryRepository.existsById(id);
    }
}
