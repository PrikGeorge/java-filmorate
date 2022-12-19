package ru.yandex.practicum.filmorate.service.genre;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */
public interface GenreService {

    /**
     * Поиск всех жанров
     *
     * @return List
     */
    List<Genre> getAll();

    /**
     * Получить жанр по идентификатору
     *
     * @param id
     * @return Genre
     */
    Genre findById(Long id);

}
