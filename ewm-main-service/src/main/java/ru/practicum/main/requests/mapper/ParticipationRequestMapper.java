package ru.practicum.main.requests.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.main.events.mapper.EventMapper;
import ru.practicum.main.requests.dto.ParticipationRequestDto;
import ru.practicum.main.requests.model.ParticipationRequest;
import ru.practicum.main.users.mapper.UserMapper;


@Mapper(componentModel = "spring", uses = {EventMapper.class, UserMapper.class})
public interface ParticipationRequestMapper {
    @Mapping(target = "event", source = "event.id")
    @Mapping(target = "id", source = "request.id")
    @Mapping(target = "requester", source = "requester.id")
    @Mapping(target = "created", source = "request.created")
    ParticipationRequestDto toDto(ParticipationRequest request);
}