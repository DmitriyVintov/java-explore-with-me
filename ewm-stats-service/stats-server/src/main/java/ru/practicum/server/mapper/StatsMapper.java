package ru.practicum.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.server.model.Hit;
import ru.practicum.server.model.ViewStats;
import ru.practicum.utility.FormatDateTime;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatsMapper {
    @Mapping(target = "timestamp", source = "timestamp", dateFormat = FormatDateTime.DATE_TIME_FORMAT)
    Hit toHit(EndpointHitDto endpointHitDto);

    List<ViewStatsDto> toViewStatsDtoList(List<ViewStats> viewStats);
}
