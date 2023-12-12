package ru.practicum.main.events.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.events.dto.*;
import ru.practicum.main.events.service.EventService;
import ru.practicum.main.requests.dto.ParticipationRequestDto;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserEventController {
    private final EventService service;

    @GetMapping
    public List<EventShortDto> getEventsByUser(@Positive @PathVariable long userId,
                                               @PositiveOrZero @RequestParam(defaultValue = "0") int from,
                                               @Positive @RequestParam(defaultValue = "10") int size) {
        List<EventShortDto> eventsByUser = service.getEventsByUser(userId, from, size);
        log.info(String.format("Запрос getEventsByUser (userId=%s, from=%s, size=%s):%n-response: %s",
                userId, from, size, eventsByUser));
        return eventsByUser;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto addByUser(@Positive @PathVariable long userId,
                                  @Valid @RequestBody NewEventDto event) {
        EventFullDto eventFullDto = service.addByUser(userId, event);
        log.info(String.format("Запрос addByUser (userId=%s):%n-request: %s,%n-response: %s",
                userId, event, eventFullDto));
        return eventFullDto;
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventByIdByUser(@Positive @PathVariable long userId,
                                           @Positive @PathVariable long eventId,
                                           HttpServletRequest request) {
        EventFullDto eventByIdByUser = service.getEventByIdByUser(userId, eventId, request);
        log.info(String.format("Запрос getEventByIdByUser (userId=%s, eventId=%s)%n-response: %s",
                userId, eventId, eventByIdByUser));
        return eventByIdByUser;
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventByUser(@Positive @PathVariable long userId,
                                          @Positive @PathVariable long eventId,
                                          @Valid @RequestBody UpdateEventUserRequest event) {
        EventFullDto eventFullDto = service.updateEventByUser(userId, eventId, event);
        log.info(String.format("Запрос updateEventByUser (userId=%s, eventId=%s):%n-request: %s,%n-response: %s",
                userId, eventId, event, eventFullDto));
        return eventFullDto;
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getRequestsByEventIdByUser(@Positive @PathVariable long userId,
                                                                    @Positive @PathVariable long eventId) {
        List<ParticipationRequestDto> requestsByEventIdByUser = service.getRequestsByEventIdByUser(userId, eventId);
        log.info(String.format("Запрос getRequestsByEventIdByUser (userId=%s, eventId=%s):%n-response: %s",
                userId, eventId, requestsByEventIdByUser));
        return requestsByEventIdByUser;
    }

    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult conformRequestsByUser(@Positive @PathVariable long userId,
                                                                @Positive @PathVariable long eventId,
                                                                @Valid @RequestBody EventRequestStatusUpdateRequest updateRequest) {
        EventRequestStatusUpdateResult eventRequestStatusUpdateResult = service.conformRequestsByUser(userId, eventId, updateRequest);
        log.info(String.format("Запрос conformRequestsByUser (userId=%s, eventId=%s):%n-request: %s,%n-response: %s",
                userId, eventId, updateRequest, eventRequestStatusUpdateResult));
        return eventRequestStatusUpdateResult;
    }
}