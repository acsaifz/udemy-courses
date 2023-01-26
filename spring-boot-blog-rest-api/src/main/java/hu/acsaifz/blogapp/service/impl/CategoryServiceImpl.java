package hu.acsaifz.blogapp.service.impl;

import hu.acsaifz.blogapp.exception.ResourceNotFoundException;
import hu.acsaifz.blogapp.model.Category;
import hu.acsaifz.blogapp.model.dto.category.CategoryDto;
import hu.acsaifz.blogapp.model.dto.category.CreateCategoryDto;
import hu.acsaifz.blogapp.model.dto.category.UpdateCategoryDto;
import hu.acsaifz.blogapp.repository.CategoryRepository;
import hu.acsaifz.blogapp.service.CategoryService;
import hu.acsaifz.blogapp.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CreateCategoryDto createCategoryDto) {
        Category result = categoryRepository.save(
                categoryMapper.toCategory(createCategoryDto)
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
    public CategoryDto updateCategory(UpdateCategoryDto updateCategoryDto, long id) {
        Category result = findCategoryById(id);
        categoryMapper.updateCategoryFromDto(updateCategoryDto, result);
        result = categoryRepository.save(result);
        return categoryMapper.toDto(result);
    }

    @Override
    public void deleteCategoryById(long id) {

    }

    private Category findCategoryById(long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }
}
