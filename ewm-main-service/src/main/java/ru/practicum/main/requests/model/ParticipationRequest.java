package ru.practicum.main.requests.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.main.events.model.Event;
import ru.practicum.main.requests.model.enums.RequestStatus;
import ru.practicum.main.users.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requests", schema = "public")
@Builder
@Getter
@Setter
public class ParticipationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;
    @ManyToOne
    @JoinColumn(name = "requester_id")
    User requester;
    @Column(name = "created")
    LocalDateTime created;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    RequestStatus status;
}