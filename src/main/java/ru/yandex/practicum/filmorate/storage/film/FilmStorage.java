package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.Storage;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 10.11.2022
 */

public interface FilmStorage extends Storage<Film> {

    /**
     * Получение списка популярных фильмов
     *
     * @param limit   количество фильмов, которое нужно вернуть
     * @param genreId
     * @param year
     * @return List
     */
    List<Film> getMostPopularFilms(Integer limit, Integer genreId, Integer year);

    /**
     * Добавление лайка пользователя к фильму
     *
     * @param filmId
     * @param userId
     * @return boolean
     */
    boolean addLike(Long filmId, Long userId);

    /**
     * Удаление лайка пользователя у фильма
     *
     * @param filmId
     * @param userId
     * @return boolean
     */
    boolean removeLike(Long filmId, Long userId);
}
