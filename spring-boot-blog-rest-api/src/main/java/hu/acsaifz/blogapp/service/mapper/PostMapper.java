package hu.acsaifz.blogapp.service.mapper;

import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.PaginatedPostsDto;
import hu.acsaifz.blogapp.model.dto.PostDto;
import hu.acsaifz.blogapp.model.dto.UpdatePostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    Post toPost(CreatePostDto dto);

    PostDto toDto(Post post);

    List<PostDto> toDto(List<Post> posts);

    @Mapping(target = "id", ignore = true)
    void updatePostFromDto(UpdatePostDto dto, @MappingTarget Post post);

    @Mapping(target = "posts", source = "content")
    @Mapping(target = "pageNo", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "last", source = "last")
    PaginatedPostsDto toDto(Page<Post> paginatedPosts);
}
