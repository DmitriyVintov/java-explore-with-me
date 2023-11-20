package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.HitDto;
import ru.practicum.model.ViewStat;
import ru.practicum.service.StatsService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.utility.DateTimeFormat.DATE_TIME_FORMAT;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class StatsController {
    private final StatsService service;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public HitDto addHit(@RequestBody @Valid HitDto hitDto) {
        log.info(String.format("Получен запрос на сохранение запроса к эндпоинту: %s", hitDto));
        return service.addHit(hitDto);
    }

    @GetMapping("/stats")
    public List<ViewStat> getStats(@RequestParam @DateTimeFormat(pattern = DATE_TIME_FORMAT) LocalDateTime start,
                                   @RequestParam @DateTimeFormat(pattern = DATE_TIME_FORMAT) LocalDateTime end,
                                   @RequestParam(required = false) List<String> uris,
                                   @RequestParam(value = "unique", defaultValue = "false") boolean uniqueHits) {
        log.info(String.format("Получен запрос на получение статистики с %s по %s для uris: %s (уникальные=%s)",
                start, end, uris, uniqueHits));
        return service.getStats(start, end, uris, uniqueHits);
    }
}
