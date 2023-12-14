package ru.practicum.main.compilations.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.compilations.dto.CompilationDto;
import ru.practicum.main.compilations.service.CompilationService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
@Validated
@Slf4j
public class PublicCompilationController {
    private final CompilationService service;

    @GetMapping
    public List<CompilationDto> getCompilationsPublic(@RequestParam(required = false) Boolean pinned,
                                                      @PositiveOrZero @RequestParam(defaultValue = "0") int from,
                                                      @Positive @RequestParam(defaultValue = "10") int size) {
        List<CompilationDto> compilations = service.getCompilationsPublic(pinned, from, size);
        log.info(String.format("Запрос getCompilationsPublic (pinned=%s, from=%s, size=%s): %s",
                pinned, from, size, compilations));
        return compilations;
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilationByIdPublic(@PathVariable long compId) {
        log.info(String.format("Запрос getCompilationByIdPublic %s", compId));
        return service.getCompilationByIdPublic(compId);
    }
}