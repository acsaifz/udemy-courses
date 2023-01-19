package hu.acsaifz.blogapp.controller;

import hu.acsaifz.blogapp.model.dto.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.PostDto;
import hu.acsaifz.blogapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostDto createPostDto){
        return new ResponseEntity<>(postService.createPost(createPostDto), HttpStatus.CREATED);
    }
}
