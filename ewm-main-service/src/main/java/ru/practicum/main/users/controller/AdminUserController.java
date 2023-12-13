package ru.practicum.main.users.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.users.dto.NewUserRequestDto;
import ru.practicum.main.users.dto.UserDto;
import ru.practicum.main.users.service.UserService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
@Slf4j
public class AdminUserController {
    private final UserService service;

    @GetMapping
    public List<UserDto> get(@RequestParam(required = false) List<Long> ids,
                             @RequestParam(defaultValue = "0") int from,
                             @RequestParam(defaultValue = "10") int size) {
        List<UserDto> userDtos = service.get(ids, from, size);
        log.info(String.format("Запрос get: (ids=%s, from=%s, size=%s)%n-response: %s",
                ids, from, size, userDtos));
        return userDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto add(@Valid @RequestBody NewUserRequestDto newUser) {
        UserDto add = service.add(newUser);
        log.info(String.format("Запрос add:%n-request: %s%n-response: %s", newUser, add));
        return add;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long userId) {
        log.info(String.format("Запрос delete: (userId=%s)", userId));
        service.delete(userId);
    }
}