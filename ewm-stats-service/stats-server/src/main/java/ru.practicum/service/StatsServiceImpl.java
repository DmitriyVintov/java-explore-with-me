package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.HitDto;
import ru.practicum.exception.ValidationException;
import ru.practicum.mapper.StatsMapper;
import ru.practicum.model.ViewStat;
import ru.practicum.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository repository;
    private final StatsMapper mapper;

    @Override
    public HitDto addHit(HitDto hitDto) {
        return mapper.toHitDto(repository.save(mapper.toHit(hitDto)));
    }

    @Override
    public List<ViewStat> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean uniqueHits) {
        if (start.isAfter(end)) {
            throw new ValidationException("Дата начала не может быть позже даты окончания");
        }
        return uniqueHits ? repository.getStatsUnique(start, end, uris) : repository.getStatsAll(start, end, uris);
    }
}
