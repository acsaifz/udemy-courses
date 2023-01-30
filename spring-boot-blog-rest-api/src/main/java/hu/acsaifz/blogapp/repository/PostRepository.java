package hu.acsaifz.blogapp.repository;

import hu.acsaifz.blogapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByCategory_Id(long categoryId);

    boolean existsPostByTitle(String title);

}
