package ru.yandex.practicum.filmorate.storage;

import java.util.List;
import java.util.Optional;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 10.11.2022
 */

public interface Storage<T> {

    /**
     * Получение списка всех объектов
     *
     * @return List
     */
    List<T> getAll();

    /**
     * Создание объекта
     *
     * @param obj
     * @return созданный объект
     */
    T create(T obj);

    /**
     * Обновление объекта
     *
     * @param obj
     * @return обнавленный объект
     */
    T update(T obj);


    /**
     * Получение объекта по id
     *
     * @param id
     * @return Optional
     */
    Optional<T> findById(Long id);

}
