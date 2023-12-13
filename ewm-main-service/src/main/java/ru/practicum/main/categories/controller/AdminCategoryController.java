package ru.practicum.main.categories.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.categories.dto.CategoryDto;
import ru.practicum.main.categories.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/admin/categories")
@RequiredArgsConstructor
@Slf4j
public class AdminCategoryController {
    private final CategoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto addCategoryByAdmin(@Valid @RequestBody CategoryDto category) {
        log.info(String.format("Запрос addCategoryByAdmin: %s", category));
        return service.addCategoryByAdmin(category);
    }

    @PatchMapping("/{catId}")
    public CategoryDto updateCategoryByAdmin(@PathVariable long catId,
                                             @Valid @RequestBody CategoryDto category) {
        log.info(String.format("Запрос updateCategoryByAdmin: (catId=%s)%n-response: %s", catId, category));
        return service.updateCategoryByAdmin(catId, category);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryByAdmin(@PathVariable long catId) {
        log.info(String.format("Запрос deleteCategoryByAdmin: %s", catId));
        service.deleteCategoryByAdmin(catId);
    }
}