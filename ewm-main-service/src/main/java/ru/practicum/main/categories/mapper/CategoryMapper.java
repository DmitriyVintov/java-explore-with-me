package ru.practicum.main.categories.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ru.practicum.main.categories.dto.CategoryDto;
import ru.practicum.main.categories.model.Category;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toCategory(CategoryDto dto);

    List<CategoryDto> toCategoryDtoList(Page<Category> categories);
}