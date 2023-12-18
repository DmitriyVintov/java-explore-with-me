package ru.practicum.main.comments.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.main.events.dto.EventShortDto;
import ru.practicum.main.users.dto.UserShortDto;
import ru.practicum.utility.FormatDateTime;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponseDto {
    Long id;
    EventShortDto event;
    UserShortDto author;
    String text;
    @DateTimeFormat(pattern = FormatDateTime.DATE_TIME_FORMAT)
    LocalDateTime created;
    @DateTimeFormat(pattern = FormatDateTime.DATE_TIME_FORMAT)
    LocalDateTime updated;
}
