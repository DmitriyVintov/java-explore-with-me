package ru.practicum.main.events.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.constants.FormatDateTime;
import ru.practicum.main.events.dto.EventFullDto;
import ru.practicum.main.events.dto.EventShortDto;
import ru.practicum.main.events.model.enums.SortEventsBy;
import ru.practicum.main.events.service.EventService;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
@Slf4j
public class PublicEventController {
    private final EventService service;

    @GetMapping
    public List<EventShortDto> getEventsPublic(@RequestParam(required = false) String text,
                                               @RequestParam(required = false) List<Long> categories,
                                               @RequestParam(required = false) Boolean paid,
                                               @RequestParam(required = false) @DateTimeFormat(pattern = FormatDateTime.DATE_TIME_FORMAT) LocalDateTime rangeStart,
                                               @RequestParam(required = false) @DateTimeFormat(pattern = FormatDateTime.DATE_TIME_FORMAT) LocalDateTime rangeEnd,
                                               @RequestParam(defaultValue = "false") boolean onlyAvailable,
                                               @RequestParam(required = false) SortEventsBy sort,
                                               @RequestParam(defaultValue = "0") int from,
                                               @RequestParam(defaultValue = "10") int size,
                                               HttpServletRequest request) {
        List<EventShortDto> eventsPublic = service.getEventsPublic(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size, request);
        log.info(String.format("Запрос getEventsPublic: (text=%s%n categories=%s%n paid=%s rangeStart=%s, rangeEnd=%s, " +
                        "onlyAvailable=%s sort=%s from=%s, size=%s%n-response: %s",
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size, eventsPublic));
        return eventsPublic;
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventByIdPublic(@PathVariable long eventId,
                                           HttpServletRequest request) {
        EventFullDto publicEventById = service.getEventByIdPublic(eventId, request);
        log.info(String.format("Запрос getEventByIdPublic: id=%s%n-response: %s", eventId, publicEventById));
        return publicEventById;
    }
}