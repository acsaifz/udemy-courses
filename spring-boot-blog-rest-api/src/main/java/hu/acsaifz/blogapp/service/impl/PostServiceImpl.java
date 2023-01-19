package hu.acsaifz.blogapp.service.impl;

import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.PostDto;
import hu.acsaifz.blogapp.repository.PostRepository;
import hu.acsaifz.blogapp.service.PostService;
import hu.acsaifz.blogapp.service.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public PostDto createPost(CreatePostDto createPostDto) {
        Post post = postRepository.save(postMapper.toPost(createPostDto));
        return postMapper.toDto(post);
    }
}
