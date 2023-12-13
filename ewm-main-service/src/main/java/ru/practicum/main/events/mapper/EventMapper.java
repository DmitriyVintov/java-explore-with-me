package ru.practicum.main.events.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.main.categories.mapper.CategoryMapper;
import ru.practicum.main.events.dto.EventFullDto;
import ru.practicum.main.events.dto.EventShortDto;
import ru.practicum.main.events.model.Event;
import ru.practicum.main.location.mapper.LocationMapper;
import ru.practicum.main.requests.mapper.ParticipationRequestMapper;
import ru.practicum.main.users.mapper.UserMapper;


@Mapper(componentModel = "spring",
        uses = {CategoryMapper.class, UserMapper.class, LocationMapper.class, ParticipationRequestMapper.class})

public interface EventMapper {
    @Mapping(target = "confirmedRequests", expression = "java(event.getConfirmedRequests()!= null ? event.getConfirmedRequests().size():0)")
    EventShortDto toShortDto(Event event);

    @Mapping(target = "confirmedRequests", expression = "java(event.getConfirmedRequests()!= null ? event.getConfirmedRequests().size():0)")
    EventFullDto toFullDto(Event event);
}