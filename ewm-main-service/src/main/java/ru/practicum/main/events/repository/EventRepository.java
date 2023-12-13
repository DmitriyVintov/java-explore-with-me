package ru.practicum.main.events.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.main.events.model.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    List<Event> findAllByInitiatorId(long userId, Pageable pageable);

    List<Event> findAllByIdIn(List<Long> ids);

    long countEventsByCategoryId(long catId);

    @Query("select e from Event e " +
            "where lower(e.annotation) like lower(concat('%', ?1, '%')) or " +
            "lower(e.description) like lower(concat('%', ?1, '%'))")
    List<Event> findAllByText(String text, Specification<Event> specification, Pageable pageable);
}