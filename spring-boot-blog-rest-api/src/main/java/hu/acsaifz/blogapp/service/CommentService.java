package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.Comment;
import hu.acsaifz.blogapp.model.dto.comment.CommentDto;
import hu.acsaifz.blogapp.model.dto.comment.CommentCreateRequest;
import hu.acsaifz.blogapp.model.dto.comment.CommentUpdateRequest;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentCreateRequest commentCreateRequest);

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    Comment findCommentById(long id);

    CommentDto updateCommentById(long postId, long commentId, CommentUpdateRequest commentUpdateRequest);

    void deleteCommentById(long postId, long commentId);
}
