package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 11.11.2022
 */

@RequestMapping("/default")
public interface Operations<T> {

    @PostMapping
    T create(@Valid @RequestBody T obj);

    @PutMapping
    T update(@Valid @RequestBody T obj);

    @GetMapping
    List<T> getAll();

    @GetMapping("/{id}")
    T findById(@PathVariable(value = "id") Long id);

}
