package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.post.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.post.PaginatedPostsDto;
import hu.acsaifz.blogapp.model.dto.post.PostDto;
import hu.acsaifz.blogapp.model.dto.post.UpdatePostDto;
import org.springframework.data.domain.Sort;

import java.util.List;


public interface PostService {

    PostDto createPost(CreatePostDto createPostDto);

    PaginatedPostsDto getAllPosts(int pageNo, int pageSize, String sortBy, Sort.Direction sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(UpdatePostDto updatePostDto, long id);

    void deletePostById(long id);

    Post findPostById(long id);

    List<PostDto> getAllPostsByCategoryId(long categoryId);
}
