package hu.acsaifz.blogapp.controller;

import hu.acsaifz.blogapp.model.dto.category.CategoryCreateRequest;
import hu.acsaifz.blogapp.model.dto.category.CategoryDto;
import hu.acsaifz.blogapp.model.dto.category.CategoryUpdateRequest;
import hu.acsaifz.blogapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryCreateRequest categoryCreateRequest){
        return new ResponseEntity<>(
                categoryService.createCategory(categoryCreateRequest),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable long id){
        return new ResponseEntity<>(
                categoryService.getCategoryById(id),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<>(
                categoryService.getAllCategories(),
                HttpStatus.OK
        );
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryUpdateRequest categoryUpdateRequest, @PathVariable long id){
        return new ResponseEntity<>(
                categoryService.updateCategoryById(categoryUpdateRequest, id),
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long id){
        categoryService.deleteCategoryById(id);
    }
}
