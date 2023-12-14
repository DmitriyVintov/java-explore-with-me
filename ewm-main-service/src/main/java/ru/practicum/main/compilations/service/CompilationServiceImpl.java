package ru.practicum.main.compilations.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.main.compilations.dto.CompilationDto;
import ru.practicum.main.compilations.dto.NewCompilationDto;
import ru.practicum.main.compilations.dto.UpdateCompilationRequestDto;
import ru.practicum.main.compilations.mapper.CompilationMapper;
import ru.practicum.main.compilations.model.Compilation;
import ru.practicum.main.compilations.repository.CompilationRepository;
import ru.practicum.main.events.repository.EventRepository;
import ru.practicum.main.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository repository;
    private final EventRepository eventRepository;
    private final CompilationMapper mapper;

    @Override
    public List<CompilationDto> getCompilationsPublic(Boolean pinned, int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        if (pinned == null) {
            return repository.findAll(pageable).stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
        }
        return repository.findAllByPinned(pinned, pageable).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilationByIdPublic(long compId) {
        return mapper.toDto(repository.findById(compId)
                .orElseThrow(() -> new NotFoundException(String.format("Compilation with id=%d was not found", compId))));
    }

    @Override
    public CompilationDto add(NewCompilationDto dto) {
        Compilation compilation = Compilation.builder()
                .events(dto.getEvents() == null ? new ArrayList<>() : eventRepository.findAllByIdIn(dto.getEvents()))
                .title(dto.getTitle())
                .pinned(dto.getPinned() == null ? false : dto.getPinned())
                .build();
        return mapper.toDto(repository.save(compilation));
    }

    @Override
    public void delete(long compId) {
        repository.deleteById(compId);
    }

    @Override
    public CompilationDto update(long compId, UpdateCompilationRequestDto dto) {
        Compilation compilation = repository.findById(compId)
                .orElseThrow(() -> new NotFoundException(String.format("Compilation with id=%d was not found", compId)));
        if (dto.getEvents() != null) {
            compilation.setEvents(eventRepository.findAllByIdIn(dto.getEvents()));
        }
        if (dto.getPinned() != null) {
            compilation.setPinned(dto.getPinned());
        }
        if (dto.getTitle() != null) {
            compilation.setTitle(dto.getTitle());
        }
        return mapper.toDto(repository.save(compilation));
    }
}