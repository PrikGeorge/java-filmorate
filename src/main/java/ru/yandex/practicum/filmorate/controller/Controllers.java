package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 11.11.2022
 */

@RequestMapping("/default")
public interface Controllers<T> {

    @PostMapping
    default T create(@Valid @RequestBody T obj) {
        throw new UnsupportedOperationException("Метод создания объекта не реализован.");
    }

    @PutMapping
    default T update(@Valid @RequestBody T obj) {
        throw new UnsupportedOperationException("Метод обновления объекта не реализован.");
    }

    @GetMapping
    List<T> getAll();

    @GetMapping("/{id}")
    T findById(@PathVariable(value = "id") Long id);

}
