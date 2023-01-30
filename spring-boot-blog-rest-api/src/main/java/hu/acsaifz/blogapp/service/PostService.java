package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.post.PostCreateRequest;
import hu.acsaifz.blogapp.model.dto.post.PaginatedPostsDto;
import hu.acsaifz.blogapp.model.dto.post.PostDto;
import hu.acsaifz.blogapp.model.dto.post.PostUpdateRequest;
import org.springframework.data.domain.Sort;

import java.util.List;


public interface PostService {

    PostDto createPost(PostCreateRequest postCreateRequest);

    PaginatedPostsDto getAllPosts(int pageNo, int pageSize, String sortBy, Sort.Direction sortDir);

    PostDto getPostById(long id);

    PostDto updatePostById(PostUpdateRequest postUpdateRequest, long id);

    void deletePostById(long id);

    Post findPostById(long id);

    List<PostDto> getAllPostsByCategoryId(long categoryId);
}
