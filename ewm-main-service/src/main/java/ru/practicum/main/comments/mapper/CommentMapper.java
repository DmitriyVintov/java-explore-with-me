package ru.practicum.main.comments.mapper;

import org.mapstruct.Mapper;
import ru.practicum.main.comments.dto.CommentCreateDto;
import ru.practicum.main.comments.dto.CommentResponseDto;
import ru.practicum.main.comments.model.Comment;
import ru.practicum.main.events.mapper.EventMapper;
import ru.practicum.main.users.mapper.UserMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, EventMapper.class})
public interface CommentMapper {
    CommentResponseDto toDto(Comment comment);

    Comment toComment(CommentCreateDto dto);

    List<CommentResponseDto> toCommentDtoList(List<Comment> comments);
}