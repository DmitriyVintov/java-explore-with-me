package ru.practicum.main.categories.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.main.categories.dto.CategoryDto;
import ru.practicum.main.categories.mapper.CategoryMapper;
import ru.practicum.main.categories.model.Category;
import ru.practicum.main.categories.repository.CategoryRepository;
import ru.practicum.main.events.repository.EventRepository;
import ru.practicum.main.exceptions.ForbiddenArgumentException;
import ru.practicum.main.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final EventRepository eventRepository;

    @Override
    public CategoryDto addCategoryByAdmin(CategoryDto dto) {
        return CategoryMapper.toDto(repository.save(CategoryMapper.toCategory(dto)));
    }

    @Override
    public CategoryDto updateCategoryByAdmin(long catId, CategoryDto dto) {
        Category category = CategoryMapper.toCategory(getCategoryById(catId));
        category.setName(dto.getName());
        return CategoryMapper.toDto(repository.save(category));
    }

    @Override
    public void deleteCategoryByAdmin(long catId) {
        repository.findById(catId)
                .orElseThrow(() -> new NotFoundException(String.format("Category with id=%d was not found", catId)));
        if (!eventRepository.findAllByCategoryId(catId).isEmpty()) {
            throw new ForbiddenArgumentException("The category is not empty");
        }
        repository.deleteById(catId);
    }

    @Override
    public List<CategoryDto> getAllCategories(int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        return repository.findAll(pageable).stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(long catId) {
        return CategoryMapper.toDto(repository.findById(catId)
                .orElseThrow(() -> new NotFoundException(String.format("Category with id=%d was not found", catId))));
    }
}