package ru.practicum.main.comments.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.main.comments.dto.CommentCreateDto;
import ru.practicum.main.comments.dto.CommentResponseDto;
import ru.practicum.main.comments.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PUBLIC)
@Slf4j
public class UserCommentController {
    private final CommentService commentService;

    @PostMapping("/user/{userId}/events/{eventId}/comment/")
    @ResponseStatus(HttpStatus.CREATED)
    CommentResponseDto createCommentUser(
            @PathVariable @Min(1) Long userId,
            @PathVariable @Min(1) Long eventId,
            @RequestBody @Valid CommentCreateDto dto
    ) {
        CommentResponseDto comment = commentService.createCommentUser(userId, eventId, dto);
        log.info(String.format("Запрос createComment (userId=%s, eventId=%s%n-request: %s)%n-response: %s",
                userId, eventId, dto, comment));
        return comment;
    }

    @GetMapping("/events/{eventId}/comments")
    List<CommentResponseDto> getCommentsPublic(
            @PathVariable @Min(1) Long eventId,
            @RequestParam(defaultValue = "0") @Min(0) int from,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        List<CommentResponseDto> comments = commentService.getCommentsPublic(eventId, from, size);
        log.info(String.format("Запрос getComments (eventId=%s)%n-response: %s",
                eventId, comments));
        return comments;
    }

    @GetMapping("/comment/{commentId}")
    CommentResponseDto getCommentById(
            @PathVariable @Min(1) Long commentId
    ) {
        CommentResponseDto commentById = commentService.getCommentById(commentId);
        log.info(String.format("Запрос getCommentById (eventId=%s)%n-response: %s",
                commentId, commentById));
        return commentById;
    }

    @PatchMapping("/user/{userId}/comment/{commentId}")
    CommentResponseDto updateCommentByUser(
            @PathVariable @Min(0) Long userId,
            @PathVariable @Min(0) Long commentId,
            @RequestBody @Valid CommentCreateDto dto
    ) {
        CommentResponseDto commentResponseDto = commentService.updateCommentByUser(userId, commentId, dto);
        log.info(String.format("Запрос updateComment (userId=%s, commentId=%s)-request:%s%n-response: %s",
                userId, commentId, dto, commentResponseDto));
        return commentResponseDto;
    }

    @DeleteMapping("/user/{userId}/comment/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCommentUser(
            @PathVariable @Min(1) Long userId,
            @PathVariable @Min(1) Long commentId
    ) {
        log.info(String.format("Запрос deleteCommentUser (userId=%s, commentId=%s)",
                userId, commentId));
        commentService.deleteComment(userId, commentId);
        log.info("Комментарий удален");
    }
}