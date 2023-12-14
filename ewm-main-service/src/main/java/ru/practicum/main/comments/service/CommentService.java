package ru.practicum.main.comments.service;

import ru.practicum.main.comments.dto.CommentCreateDto;
import ru.practicum.main.comments.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto createCommentUser(Long userId, Long eventId, CommentCreateDto commentCreateDto);

    List<CommentResponseDto> getCommentsPublic(Long eventId, int from, int size);

    CommentResponseDto getCommentById(Long commentId);

    CommentResponseDto updateCommentByUser(Long userId, Long commentId, CommentCreateDto commentUpdateDto);

    void deleteComment(Long userId, Long commentId);
}
