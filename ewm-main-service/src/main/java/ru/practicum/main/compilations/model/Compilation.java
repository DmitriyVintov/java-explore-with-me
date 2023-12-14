package ru.practicum.main.compilations.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.main.events.model.Event;

import javax.persistence.*;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compilations", schema = "public")
@Builder
@Getter
@Setter
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "title", nullable = false)
    String title;
    @Column(name = "pinned")
    Boolean pinned;
    @ManyToMany
    @JoinTable(name = "event_compilation",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    List<Event> events;
}