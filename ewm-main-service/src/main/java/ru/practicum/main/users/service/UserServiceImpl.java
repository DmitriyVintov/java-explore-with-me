package ru.practicum.main.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.main.users.dto.NewUserRequestDto;
import ru.practicum.main.users.dto.UserDto;
import ru.practicum.main.users.mapper.UserMapper;
import ru.practicum.main.users.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public List<UserDto> get(List<Long> ids, int from, int size) {
        List<UserDto> users;
        Pageable pageable = PageRequest.of(from / size, size, Sort.by(Sort.Direction.ASC, "id"));
        if (ids == null) {
            users = repository.findAll(pageable).stream()
                    .map(mapper::toUserDto)
                    .collect(Collectors.toList());
        } else {
            users = repository.findAllByIdIn(ids, pageable).stream()
                    .map(mapper::toUserDto)
                    .collect(Collectors.toList());
        }
        return users;
    }

    @Override
    public UserDto add(NewUserRequestDto newUser) {
        return mapper.toUserDto(repository.save(mapper.toUser(newUser)));
    }

    @Override
    public void delete(long userId) {
        repository.deleteById(userId);
    }
}