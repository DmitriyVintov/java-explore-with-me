package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.Hit;
import ru.practicum.model.ViewStat;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<Hit, Long> {

    @Query(value = "select new ru.practicum.model.ViewStat(h.app, h.uri, count(distinct h.ip)) " +
            "from Hit h " +
            "where h.timestamp between :start and :end " +
            "and ((:uris) is null or h.uri in :uris) " +
            "group by h.ip, h.app, h.uri " +
            "order by count(distinct h.ip) desc")
    List<ViewStat> getStatsUnique(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(value = "select new ru.practicum.model.ViewStat(h.app, h.uri, count(h.ip)) " +
            "from Hit h " +
            "where h.timestamp between :start and :end " +
            "and ((:uris) is null or h.uri in :uris) " +
            "group by h.app, h.uri " +
            "order by count(h.ip) desc")
    List<ViewStat> getStatsAll(LocalDateTime start, LocalDateTime end, List<String> uris);
}
