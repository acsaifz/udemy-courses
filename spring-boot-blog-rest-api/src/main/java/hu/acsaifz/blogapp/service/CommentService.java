package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.Comment;
import hu.acsaifz.blogapp.model.dto.comment.CommentDto;
import hu.acsaifz.blogapp.model.dto.comment.CreateCommentDto;
import hu.acsaifz.blogapp.model.dto.comment.UpdateCommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CreateCommentDto createCommentDto);

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    Comment findCommentById(long id);

    CommentDto updateComment(long postId, long commentId, UpdateCommentDto updateCommentDto);
}
