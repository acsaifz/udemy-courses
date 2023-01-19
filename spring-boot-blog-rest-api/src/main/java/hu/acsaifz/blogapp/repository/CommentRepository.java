package hu.acsaifz.blogapp.repository;

import hu.acsaifz.blogapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
