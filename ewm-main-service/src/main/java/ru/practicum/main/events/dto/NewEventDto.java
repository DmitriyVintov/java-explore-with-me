package ru.practicum.main.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import ru.practicum.main.constants.FormatDateTime;
import ru.practicum.main.location.dto.LocationDto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewEventDto {
    @NotBlank
    @Length(min = 20, max = 2000)
    String annotation;
    @NotNull
    @PositiveOrZero
    long category;
    @NotBlank
    @Length(min = 20, max = 7000)
    String description;
    @NotNull
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FormatDateTime.DATE_TIME_FORMAT)
    LocalDateTime eventDate;
    @NotNull
    LocationDto location;
    Boolean paid;
    @PositiveOrZero
    Integer participantLimit;
    Boolean requestModeration;
    @NotBlank
    @Length(min = 3, max = 120)
    String title;
}