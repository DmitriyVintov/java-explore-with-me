package ru.practicum.main.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.utility.FormatDateTime;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ApiError {
    String status;
    String reason;
    String massage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FormatDateTime.DATE_TIME_FORMAT)
    LocalDateTime timestamp;
}