package ru.practicum.main.requests.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.requests.dto.ParticipationRequestDto;
import ru.practicum.main.requests.service.RequestService;

import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/requests")
@RequiredArgsConstructor
@Slf4j
public class UserRequestController {
    private final RequestService service;

    @GetMapping
    public List<ParticipationRequestDto> getByUserId(@PathVariable long userId) {
        List<ParticipationRequestDto> requestByUserIdByUser = service.getRequestByUserIdByUser(userId);
        log.info(String.format("Запрос getByUserId (userId=%s)%n-response: %s", userId, requestByUserIdByUser));
        return requestByUserIdByUser;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto add(@PathVariable long userId,
                                       @RequestParam long eventId) {
        ParticipationRequestDto participationRequestDto = service.addRequestByUser(userId, eventId);
        log.info(String.format("Запрос add (userId=%s, eventId=%s)%n-response: %s",
                userId, eventId, participationRequestDto));
        return participationRequestDto;
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancel(@PathVariable long userId,
                                          @PathVariable long requestId) {
        ParticipationRequestDto participationRequestDto = service.cancelRequest(userId, requestId);
        log.info(String.format("Запрос cancel (userId=%s, request=%s)%n-response: %s",
                userId, requestId, participationRequestDto));
        return participationRequestDto;
    }
}