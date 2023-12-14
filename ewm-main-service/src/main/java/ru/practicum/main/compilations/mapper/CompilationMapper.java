package ru.practicum.main.compilations.mapper;

import org.mapstruct.Mapper;
import ru.practicum.main.compilations.dto.CompilationDto;
import ru.practicum.main.compilations.model.Compilation;
import ru.practicum.main.events.mapper.EventMapper;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface CompilationMapper {
    CompilationDto toDto(Compilation compilation);
}