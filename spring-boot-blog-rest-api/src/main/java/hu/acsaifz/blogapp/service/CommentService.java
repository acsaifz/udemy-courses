package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.dto.comment.CommentDto;
import hu.acsaifz.blogapp.model.dto.comment.CreateCommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CreateCommentDto createCommentDto);
}
