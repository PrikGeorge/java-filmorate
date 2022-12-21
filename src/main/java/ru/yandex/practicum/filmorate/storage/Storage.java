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
    default T create(T obj) {
        throw new UnsupportedOperationException("Метод создания объекта не реализован.");
    }

    /**
     * Обновление объекта
     *
     * @param obj
     * @return обнвленный объект
     */
    default Optional<T> update(T obj) {
        throw new UnsupportedOperationException("Метод обновления объекта не реализован.");
    }

    /**
     * Получение объекта по id
     *
     * @param id
     * @return Optional
     */
    Optional<T> findById(Long id);

    /**
     * Удаление объекта по id
     *
     * @param id
     */
    default void remove(Long id) {
        throw new UnsupportedOperationException("Метод удаления объекта не реализован.");
    }

}
