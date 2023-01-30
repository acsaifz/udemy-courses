package hu.acsaifz.blogapp.controller;

import hu.acsaifz.blogapp.model.dto.comment.CommentDto;
import hu.acsaifz.blogapp.model.dto.comment.CommentCreateRequest;
import hu.acsaifz.blogapp.model.dto.comment.CommentUpdateRequest;
import hu.acsaifz.blogapp.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @Valid @RequestBody CommentCreateRequest commentCreateRequest){
        return new ResponseEntity<>(
                commentService.createComment(postId, commentCreateRequest),
                HttpStatus.OK
        );
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable long postId){
        return commentService.getCommentByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable long postId, @PathVariable long commentId){
        return new ResponseEntity<>(
                commentService.getCommentById(postId, commentId),
                HttpStatus.OK
        );
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable long postId, @PathVariable long commentId,
                                                        @Valid @RequestBody CommentUpdateRequest commentUpdateRequest){
        return new ResponseEntity<>(
                commentService.updateCommentById(postId, commentId, commentUpdateRequest),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId){
        commentService.deleteCommentById(postId, commentId);
    }
}
