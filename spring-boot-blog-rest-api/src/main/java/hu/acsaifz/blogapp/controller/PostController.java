package hu.acsaifz.blogapp.controller;

import hu.acsaifz.blogapp.model.dto.post.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.post.PaginatedPostsDto;
import hu.acsaifz.blogapp.model.dto.post.PostDto;
import hu.acsaifz.blogapp.model.dto.post.UpdatePostDto;
import hu.acsaifz.blogapp.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static hu.acsaifz.blogapp.util.GlobalConstants.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody CreatePostDto createPostDto){
        return new ResponseEntity<>(postService.createPost(createPostDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PaginatedPostsDto getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) Sort.Direction sortDir
            ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable long id, @Valid @RequestBody UpdatePostDto updatePostDto){
        return new ResponseEntity<>(postService.updatePost(updatePostDto, id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable long id){
        postService.deletePostById(id);
    }
}
