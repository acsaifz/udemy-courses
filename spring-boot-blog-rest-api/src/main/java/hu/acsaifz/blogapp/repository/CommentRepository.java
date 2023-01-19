package hu.acsaifz.blogapp.repository;

import hu.acsaifz.blogapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostId(long id);
}
