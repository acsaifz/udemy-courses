package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.dto.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.PostDto;

import java.util.List;


public interface PostService {

    PostDto createPost(CreatePostDto createPostDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long id);

}
