package hu.acsaifz.blogapp.service.impl;

import hu.acsaifz.blogapp.exception.BlogApiException;
import hu.acsaifz.blogapp.exception.ResourceNotFoundException;
import hu.acsaifz.blogapp.model.Comment;
import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.comment.CommentDto;
import hu.acsaifz.blogapp.model.dto.comment.CreateCommentDto;
import hu.acsaifz.blogapp.repository.CommentRepository;
import hu.acsaifz.blogapp.service.CommentService;
import hu.acsaifz.blogapp.service.PostService;
import hu.acsaifz.blogapp.service.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        List<Comment> results = commentRepository.findAllByPostId(postId);
        return commentMapper.toDto(results);
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post actualPost = postService.findPostById(postId);

        Comment actualComment = findCommentById(commentId);

        if (!actualPost.equals(actualComment.getPost())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to this post.");
        }

        return commentMapper.toDto(actualComment);
    }

    @Override
    public Comment findCommentById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
    }
}
