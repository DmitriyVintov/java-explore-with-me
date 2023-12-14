package ru.practicum.main.events.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.constants.FormatDateTime;
import ru.practicum.main.events.dto.EventFullDto;
import ru.practicum.main.events.dto.UpdateEventAdminRequest;
import ru.practicum.main.events.model.enums.State;
import ru.practicum.main.events.service.EventService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/events")
@RequiredArgsConstructor
@Slf4j
public class AdminEventController {
    private final EventService service;

    @GetMapping
    public List<EventFullDto> getEventsByAdmin(@RequestParam(required = false) List<Long> users,
                                               @RequestParam(required = false) List<State> states,
                                               @RequestParam(required = false) List<Long> categories,
                                               @RequestParam(required = false) @DateTimeFormat(pattern = FormatDateTime.DATE_TIME_FORMAT) LocalDateTime rangeStart,
                                               @RequestParam(required = false) @DateTimeFormat(pattern = FormatDateTime.DATE_TIME_FORMAT) LocalDateTime rangeEnd,
                                               @RequestParam(defaultValue = "0") int from,
                                               @RequestParam(defaultValue = "10") int size) {
        List<EventFullDto> adminEvents = service.getEventsByAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
        log.info(String.format("Запрос getEventsByAdmin: (users=%s%n states=%s%n rangeStart=%s, rangeEnd=%s, from=%s, size=%s)%n -response: %s",
                users, states, rangeStart, rangeEnd, from, size, adminEvents));
        return adminEvents;
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventByAdmin(@PathVariable long eventId,
                                           @Valid @RequestBody UpdateEventAdminRequest event) {
        EventFullDto eventFullDto = service.updateEventByAdmin(eventId, event);
        log.info(String.format("Запрос updateEventByAdmin: eventId=%s, dto: %s%n -response: %s",
                eventId, event, eventFullDto));
        return eventFullDto;
    }
}