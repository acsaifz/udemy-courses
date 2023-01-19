package hu.acsaifz.blogapp.service.impl;

import hu.acsaifz.blogapp.model.Comment;
import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.comment.CommentDto;
import hu.acsaifz.blogapp.model.dto.comment.CreateCommentDto;
import hu.acsaifz.blogapp.repository.CommentRepository;
import hu.acsaifz.blogapp.service.CommentService;
import hu.acsaifz.blogapp.service.PostService;
import hu.acsaifz.blogapp.service.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final CommentMapper commentMapper;

    @Override
    public CommentDto createComment(long postId, CreateCommentDto createCommentDto) {
        Comment comment = commentMapper.toComment(createCommentDto);

        Post actualPost = postService.findPostById(postId);
        actualPost.addComment(comment);

        return commentMapper.toDto(commentRepository.save(comment));
    }
}
