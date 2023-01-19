package hu.acsaifz.blogapp.service.mapper;

import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.post.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.post.PaginatedPostsDto;
import hu.acsaifz.blogapp.model.dto.post.PostDto;
import hu.acsaifz.blogapp.model.dto.post.UpdatePostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Post toPost(CreatePostDto dto);

    PostDto toDto(Post post);

    List<PostDto> toDto(List<Post> posts);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    void updatePostFromDto(UpdatePostDto dto, @MappingTarget Post post);

    @Mapping(target = "posts", source = "content")
    @Mapping(target = "pageNo", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "last", source = "last")
    PaginatedPostsDto toDto(Page<Post> paginatedPosts);
}
