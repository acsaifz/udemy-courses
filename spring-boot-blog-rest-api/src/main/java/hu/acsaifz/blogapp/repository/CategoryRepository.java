package hu.acsaifz.blogapp.repository;

import hu.acsaifz.blogapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsCategoryByName(String name);
}
