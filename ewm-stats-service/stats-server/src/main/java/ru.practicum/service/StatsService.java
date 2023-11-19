package ru.practicum.service;

import ru.practicum.HitDto;
import ru.practicum.model.ViewStat;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    HitDto addHit(HitDto hitDto);

    List<ViewStat> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean uniqueHits);
}
