package hu.acsaifz.blogapp.controller;

import hu.acsaifz.blogapp.model.dto.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.PostDto;
import hu.acsaifz.blogapp.model.dto.UpdatePostDto;
import hu.acsaifz.blogapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostDto createPostDto){
        return new ResponseEntity<>(postService.createPost(createPostDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable long id, @RequestBody UpdatePostDto updatePostDto){
        return new ResponseEntity<>(postService.updatePost(updatePostDto, id), HttpStatus.OK);
    }

}
