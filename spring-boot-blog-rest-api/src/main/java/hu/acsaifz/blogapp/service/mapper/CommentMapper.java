package hu.acsaifz.blogapp.service.mapper;

import hu.acsaifz.blogapp.model.Comment;
import hu.acsaifz.blogapp.model.dto.comment.CommentDto;
import hu.acsaifz.blogapp.model.dto.comment.CreateCommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "post", ignore = true)
    Comment toComment(CreateCommentDto dto);

    CommentDto toDto(Comment comment);

    List<CommentDto> toDto(List<Comment> comments);
}
