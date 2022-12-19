package ru.yandex.practicum.filmorate.service.mpa;

import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

public interface MpaService {

    /**
     * Поиск всех рейтинов
     *
     * @return List
     */
    List<Mpa> getAll();

    /**
     * Получить рейтинг по индентификатору
     *
     * @param id
     * @return Mpa
     */
    Mpa findById(Long id);

}
