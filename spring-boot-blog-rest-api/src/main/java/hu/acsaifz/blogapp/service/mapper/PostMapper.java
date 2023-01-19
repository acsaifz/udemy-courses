package hu.acsaifz.blogapp.service.mapper;

import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.PostDto;
import hu.acsaifz.blogapp.model.dto.UpdatePostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    Post toPost(CreatePostDto dto);

    PostDto toDto(Post post);

    List<PostDto> toDto(List<Post> posts);

    @Mapping(target = "id", ignore = true)
    void updatePostFromDto(UpdatePostDto dto, @MappingTarget Post post);

}
