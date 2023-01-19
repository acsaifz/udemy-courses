package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.dto.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.PostDto;
import hu.acsaifz.blogapp.model.dto.UpdatePostDto;

import java.util.List;


public interface PostService {

    PostDto createPost(CreatePostDto createPostDto);

    List<PostDto> getAllPosts(int pageNo, int pageSize);

    PostDto getPostById(long id);

    PostDto updatePost(UpdatePostDto updatePostDto, long id);

    void deletePostById(long id);
}
