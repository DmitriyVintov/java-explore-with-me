package ru.practicum.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

import static ru.practicum.utility.DateTimeFormat.DATE_TIME_FORMAT;

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
    Integer id;
    @Column(name = "app")
    String app;
    @Column(name = "uri")
    String uri;
    @Column(name = "ip")
    String ip;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @Column(name = "timestamp")
    LocalDateTime timestamp;
}
