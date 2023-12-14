package ru.practicum.server.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

import static ru.practicum.utility.FormatDateTime.DATE_TIME_FORMAT;

@Entity
@Table(name = "hits", schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String app;
    String uri;
    String ip;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    LocalDateTime timestamp;
}
