package hu.acsaifz.blogapp.service.impl;

import hu.acsaifz.blogapp.exception.ResourceNotFoundException;
import hu.acsaifz.blogapp.model.Category;
import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.post.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.post.PaginatedPostsDto;
import hu.acsaifz.blogapp.model.dto.post.PostDto;
import hu.acsaifz.blogapp.model.dto.post.UpdatePostDto;
import hu.acsaifz.blogapp.repository.PostRepository;
import hu.acsaifz.blogapp.service.CategoryService;
import hu.acsaifz.blogapp.service.PostService;
import hu.acsaifz.blogapp.service.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final PostMapper postMapper;

    @Override
    public PostDto createPost(CreatePostDto createPostDto) {
        Category category = categoryService.findCategoryById(createPostDto.getCategoryId());
        Post post = postMapper.toPost(createPostDto);

        post.setCategory(category);

        return postMapper.toDto(
                postRepository.save(post)
        );
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
        Category category = categoryService.findCategoryById(updatePostDto.getCategoryId());
        Post result = findPostById(id);
        postMapper.updatePostFromDto(updatePostDto, result);

        if (!category.equals(result.getCategory())){
            result.setCategory(category);
        }

        result = postRepository.save(result);
        return postMapper.toDto(result);
    }

    @Override
    public void deletePostById(long id) {
        Post result = findPostById(id);
        postRepository.delete(result);
    }

    @Override
    public Post findPostById(long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    @Override
    public List<PostDto> getAllPostsByCategoryId(long categoryId) {
        if (!categoryService.existsCategoryById(categoryId)){
            throw new ResourceNotFoundException("Category", "id", categoryId);
        }

        List<Post> result = postRepository.findAllByCategory_Id(categoryId);
        return postMapper.toDto(result);
    }
}
