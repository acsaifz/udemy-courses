package hu.acsaifz.blogapp.service.mapper;

import hu.acsaifz.blogapp.model.Post;
import hu.acsaifz.blogapp.model.dto.CreatePostDto;
import hu.acsaifz.blogapp.model.dto.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toPost(CreatePostDto dto);

    PostDto toDto(Post post);

}
