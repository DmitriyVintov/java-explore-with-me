package ru.practicum.main.exceptions.handler;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.main.exceptions.ForbiddenArgumentException;
import ru.practicum.main.exceptions.NotFoundException;
import ru.practicum.main.exceptions.ValidationRequestException;
import ru.practicum.main.exceptions.dto.ApiError;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final NotFoundException e) {
        log.error("NotFoundException!, {}", e.getMessage());
        return new ApiError(HttpStatus.NOT_FOUND.name(), "Объект не найден",
                e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(final ValidationException e) {
        log.error("ValidationException!, {}", e.getMessage());
        return new ApiError(HttpStatus.BAD_REQUEST.name(), "Запрос составлен некорректно",
                e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationRequestException(final ValidationRequestException e) {
        log.error("ValidationException!, {}", e.getMessage());
        return new ApiError(HttpStatus.BAD_REQUEST.name(), "Запрос составлен некорректно",
                e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException!, {}", e.getMessage());
        return new ApiError(HttpStatus.BAD_REQUEST.name(), "Запрос составлен некорректно",
                e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConstraintViolationException(final ConstraintViolationException e) {
        log.error("ConstraintViolationException!, {}", e.getMessage());
        return new ApiError(HttpStatus.CONFLICT.name(), "Нарушение целостности данных",
                e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleForbiddenArgumentException(final ForbiddenArgumentException e) {
        log.error("ForbiddenArgumentException!, {}", e.getMessage());
        return new ApiError(HttpStatus.CONFLICT.name(), "Нарушение целостности данных",
                e.getMessage(), LocalDateTime.now());
    }
}