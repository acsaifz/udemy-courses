package hu.acsaifz.blogapp.service.impl;

import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.PostDto;
import hu.acsaifz.blogapp.repository.PostRepository;
import hu.acsaifz.blogapp.service.PostService;
import hu.acsaifz.blogapp.service.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    @Transactional
    public PostDto createPost(CreatePostDto createPostDto) {
        Post result = postRepository.save(postMapper.toPost(createPostDto));
        return postMapper.toDto(result);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> result = postRepository.findAll();
        return postMapper.toDto(result);
    }
}
