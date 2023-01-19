package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.dto.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.PaginatedPostsDto;
import hu.acsaifz.blogapp.model.dto.PostDto;
import hu.acsaifz.blogapp.model.dto.UpdatePostDto;


public interface PostService {

    PostDto createPost(CreatePostDto createPostDto);

    PaginatedPostsDto getAllPosts(int pageNo, int pageSize);

    PostDto getPostById(long id);

    PostDto updatePost(UpdatePostDto updatePostDto, long id);

    void deletePostById(long id);
}
