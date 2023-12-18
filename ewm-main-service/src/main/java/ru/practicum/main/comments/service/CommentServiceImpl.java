package ru.practicum.main.comments.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.main.comments.dto.CommentCreateDto;
import ru.practicum.main.comments.dto.CommentResponseDto;
import ru.practicum.main.comments.mapper.CommentMapper;
import ru.practicum.main.comments.model.Comment;
import ru.practicum.main.comments.repository.CommentRepository;
import ru.practicum.main.events.model.Event;
import ru.practicum.main.events.repository.EventRepository;
import ru.practicum.main.exceptions.NotFoundException;
import ru.practicum.main.exceptions.ValidationRequestException;
import ru.practicum.main.users.model.User;
import ru.practicum.main.users.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CommentMapper mapper;

    @Override
    public CommentResponseDto createCommentUser(Long userId, Long eventId, CommentCreateDto commentCreateDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(String.format("Пользователь id %d не найден", userId)));
        Event event = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException(String.format("Мероприятие id %d не найдено", eventId)));
        Comment comment = mapper.toComment(commentCreateDto);
        comment.setAuthor(user);
        comment.setEvent(event);
        comment.setCreated(LocalDateTime.now());
        return mapper.toDto(repository.save(comment));
    }

    @Override
    public List<CommentResponseDto> getCommentsPublic(Long eventId, int from, int size) {
        existEvent(eventId);
        Pageable pageable = PageRequest.of(from / size, size);
        return mapper.toCommentDtoList(repository.findByEventId(eventId, pageable));
    }

    @Override
    public CommentResponseDto getCommentById(Long commentId) {
        Comment comment = repository.findById(commentId).orElseThrow(
                () -> new NotFoundException(String.format("Комментарий id %d не найден", commentId)));
        return mapper.toDto(repository.save(comment));
    }

    @Override
    public CommentResponseDto updateCommentByUser(Long userId, Long commentId, CommentCreateDto dto) {
        existUser(userId);
        CommentResponseDto comment = mapper.toDto(repository.findById(commentId).orElseThrow(
                () -> new NotFoundException(String.format("Комментарий id %d не найден", commentId))));
        if (!Objects.equals(comment.getAuthor().getId(), userId)) {
            throw new ValidationRequestException("Вы не можете редактировать комментарий другого пользователя");
        }
        comment.setText(dto.getText());
        comment.setUpdated(LocalDateTime.now());
        return comment;
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        existUser(userId);
        Comment comment = repository.findById(commentId).orElseThrow(
                () -> new NotFoundException(String.format("Комментарий id %d не найден", commentId)));
        if (!Objects.equals(comment.getAuthor().getId(), userId)) {
            throw new ValidationRequestException("Вы не можете удалить комментарий другого пользователя");
        }
        repository.deleteById(commentId);
    }

    private void existUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(String.format("Пользователь id %d не найден", userId));
        }
    }

    private void existEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new NotFoundException(String.format("Мероприятие id %d не найдено", eventId));
        }
    }
}
