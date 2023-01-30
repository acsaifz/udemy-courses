package hu.acsaifz.blogapp.service.mapper;

import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.post.PostCreateRequest;
import hu.acsaifz.blogapp.model.dto.post.PaginatedPostsDto;
import hu.acsaifz.blogapp.model.dto.post.PostDto;
import hu.acsaifz.blogapp.model.dto.post.PostUpdateRequest;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "category", ignore = true)
    Post toPost(PostCreateRequest dto);

    @Mapping(target = "comments", source = "comments")
    @Mapping(target = "category", source = "category")
    PostDto toDto(Post post);

    List<PostDto> toDto(List<Post> posts);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updatePostFromDto(PostUpdateRequest dto, @MappingTarget Post post);

    @Mapping(target = "posts", source = "content")
    @Mapping(target = "pageNo", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "last", source = "last")
    PaginatedPostsDto toDto(Page<Post> paginatedPosts);
}
