package ru.practicum.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.server.exception.ValidationRequestException;
import ru.practicum.server.mapper.StatsMapper;
import ru.practicum.server.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statServerRepository;
    private final StatsMapper mapper;

    @Override
    public void addHit(EndpointHitDto endpointHitDto) {
        log.debug("Save hit by app: " + endpointHitDto.getApp());
        statServerRepository.save(mapper.toHit(endpointHitDto));
    }

    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        log.debug("Received stats.");
        if (start.isAfter(end)) {
            throw new ValidationRequestException("Date start must be before date end");
        }
        if (uris == null) {
            return unique ? mapper.toViewStatsDtoList(statServerRepository.findAllUniqueIp(start, end))
                    : mapper.toViewStatsDtoList(statServerRepository.findAllNotUniqueIp(start, end));
        }
        return unique ? mapper.toViewStatsDtoList(statServerRepository.findUniqueIpByUris(start, end, uris))
                : mapper.toViewStatsDtoList(statServerRepository.findNotUniqueIpByUris(start, end, uris));
    }
}
