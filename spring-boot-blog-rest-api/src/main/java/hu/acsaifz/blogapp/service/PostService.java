package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.dto.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.PostDto;


public interface PostService {

    PostDto createPost(CreatePostDto createPostDto);

}
