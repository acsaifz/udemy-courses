package hu.acsaifz.blogapp.service.impl;

import hu.acsaifz.blogapp.exception.ResourceNotFoundException;
import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.PaginatedPostsDto;
import hu.acsaifz.blogapp.model.dto.PostDto;
import hu.acsaifz.blogapp.model.dto.UpdatePostDto;
import hu.acsaifz.blogapp.repository.PostRepository;
import hu.acsaifz.blogapp.service.PostService;
import hu.acsaifz.blogapp.service.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public PostDto createPost(CreatePostDto createPostDto) {
        Post result = postRepository.save(postMapper.toPost(createPostDto));
        return postMapper.toDto(result);
    }

    @Override
    public PaginatedPostsDto getAllPosts(int pageNo, int pageSize, String sortBy, Sort.Direction sortDir) {
        Sort sort = Sort.by(sortDir, sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> result = postRepository.findAll(pageable);
        return postMapper.toDto(result);
    }

    @Override
    public PostDto getPostById(long id) {
        Post result = findPostById(id);
        return postMapper.toDto(result);
    }

    @Override
    public PostDto updatePost(UpdatePostDto updatePostDto, long id) {
        Post result = findPostById(id);
        postMapper.updatePostFromDto(updatePostDto, result);
        result = postRepository.save(result);
        return postMapper.toDto(result);
    }

    @Override
    public void deletePostById(long id) {
        Post result = findPostById(id);
        postRepository.delete(result);
    }

    private Post findPostById(long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }
}
