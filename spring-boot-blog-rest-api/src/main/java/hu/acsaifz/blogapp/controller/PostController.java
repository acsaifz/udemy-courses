package hu.acsaifz.blogapp.controller;

import hu.acsaifz.blogapp.model.dto.post.PostCreateRequest;
import hu.acsaifz.blogapp.model.dto.post.PaginatedPostsDto;
import hu.acsaifz.blogapp.model.dto.post.PostDto;
import hu.acsaifz.blogapp.model.dto.post.PostUpdateRequest;
import hu.acsaifz.blogapp.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.acsaifz.blogapp.util.GlobalConstants.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostCreateRequest postCreateRequest){
        return new ResponseEntity<>(
                postService.createPost(postCreateRequest),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<PaginatedPostsDto> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) Sort.Direction sortDir
            ){
        return new ResponseEntity<>(
                postService.getAllPosts(pageNo, pageSize, sortBy, sortDir),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        return new ResponseEntity<>(
                postService.getPostById(id),
                HttpStatus.OK
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable long id, @Valid @RequestBody PostUpdateRequest postUpdateRequest){
        return new ResponseEntity<>(
                postService.updatePostById(postUpdateRequest, id),
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deletePostById(@PathVariable long id){
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(@PathVariable long id){
        return new ResponseEntity<>(
                postService.getAllPostsByCategoryId(id),
                HttpStatus.OK
        );
    }
}
