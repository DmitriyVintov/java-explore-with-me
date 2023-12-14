package ru.practicum.main.users.mapper;

import org.mapstruct.Mapper;
import ru.practicum.main.users.dto.NewUserRequestDto;
import ru.practicum.main.users.dto.UserDto;
import ru.practicum.main.users.dto.UserShortDto;
import ru.practicum.main.users.model.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    User toUser(NewUserRequestDto dto);

    UserShortDto toUserShortDto(User user);
}