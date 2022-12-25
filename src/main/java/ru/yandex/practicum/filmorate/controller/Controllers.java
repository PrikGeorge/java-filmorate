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

    /**
     * Создание объекта
     *
     * @param obj
     * @return T
     */
    @PostMapping
    default T create(@Valid @RequestBody T obj) {
        throw new UnsupportedOperationException("Метод создания объекта не реализован.");
    }

    /**
     * Обновление объекта
     *
     * @param obj
     * @return T
     */
    @PutMapping
    default T update(@Valid @RequestBody T obj) {
        throw new UnsupportedOperationException("Метод обновления объекта не реализован.");
    }

    /**
     * Удаление объекта
     *
     * @param id
     * @return boolean
     */
    @DeleteMapping("/{id}")
    default boolean remove(@PathVariable(value = "id") Long id) {
        throw new UnsupportedOperationException("Метод удаления объекта не реализован.");
    }

    /**
     * Получение списка всех объектов
     *
     * @return List
     */
    @GetMapping
    List<T> getAll();

    /**
     * Получение объекта по id
     *
     * @param id
     * @return T
     */
    @GetMapping("/{id}")
    T findById(@PathVariable(value = "id") Long id);

}
