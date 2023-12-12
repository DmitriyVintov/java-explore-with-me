package ru.practicum.main.categories.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.categories.dto.CategoryDto;
import ru.practicum.main.categories.service.CategoryService;


import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
@Slf4j
public class PublicCategoryController {
    private final CategoryService service;

    @GetMapping
    public List<CategoryDto> getAllCategoriesPublic(@RequestParam(defaultValue = "0") int from,
                                                    @RequestParam(defaultValue = "10") int size) {
        List<CategoryDto> categories = service.getAllCategories(from, size);
        log.info(String.format("Запрос getAllCategoriesPublic (from=%s, size=%s):%n-response: %s",
                from, size, categories));
        return categories;
    }

    @GetMapping("/{catId}")
    public CategoryDto getByIdPublic(@PathVariable long catId) {
        CategoryDto categoryById = service.getCategoryById(catId);
        log.info(String.format("Запрос getByIdPublic #%s:%n-response: %s", catId, categoryById));
        return categoryById;
    }
}