package ru.practicum.main.compilations.service;


import ru.practicum.main.compilations.dto.CompilationDto;
import ru.practicum.main.compilations.dto.NewCompilationDto;
import ru.practicum.main.compilations.dto.UpdateCompilationRequestDto;

import java.util.List;

public interface CompilationService {
    List<CompilationDto> getCompilationsPublic(Boolean pinned, int from, int size);

    CompilationDto getCompilationByIdPublic(long compId);

    CompilationDto add(NewCompilationDto dto);

    void delete(long compId);

    CompilationDto update(long compId, UpdateCompilationRequestDto dto);
}