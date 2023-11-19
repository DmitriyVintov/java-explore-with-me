package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.HitDto;
import ru.practicum.model.Hit;
import ru.practicum.utility.DateTimeFormat;

@Mapper(componentModel = "spring")
public interface StatsMapper {

    @Mapping(target = "timestamp", source = "timestamp", dateFormat = DateTimeFormat.DATE_TIME_FORMAT)
    HitDto toHitDto(Hit Hit);

    @Mapping(target = "timestamp", source = "timestamp", dateFormat = DateTimeFormat.DATE_TIME_FORMAT)
    Hit toHit(HitDto HitDto);
}
