package ru.practicum.main.compilations.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.compilations.dto.CompilationDto;
import ru.practicum.main.compilations.dto.NewCompilationDto;
import ru.practicum.main.compilations.dto.UpdateCompilationRequestDto;
import ru.practicum.main.compilations.service.CompilationService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/admin/compilations")
@RequiredArgsConstructor
@Slf4j
public class AdminCompilationController {
    private final CompilationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto addByAdmin(@Valid @RequestBody NewCompilationDto dto) {
        CompilationDto responseCompilationDto = service.add(dto);
        log.info(String.format("Запрос addByAdmin:%n-request: %s%n-response: %s", dto, responseCompilationDto));
        return responseCompilationDto;
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByAdmin(@PathVariable long compId) {
        log.info(String.format("Запрос deleteByAdmin: compId=%s", compId));
        service.delete(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto updateByAdmin(@PathVariable long compId,
                                        @Valid @RequestBody UpdateCompilationRequestDto dto) {
        CompilationDto updateCompilationDto = service.update(compId, dto);
        log.info(String.format("Запрос updateByAdmin: (compId=%s)%n-request: %s%n-response: %s",
                compId, dto, updateCompilationDto));
        return updateCompilationDto;
    }
}