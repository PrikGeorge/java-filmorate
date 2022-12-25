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
     * @param limit количество фильмов, которое нужно вернуть
     * @return List
     */
    List<Film> getMostPopularFilms(int limit);

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

    /**
     * Возвращает список фильмов режиссера отсортированных
     * по количеству лайков или году выпуска.
     *
     * @param directorId
     * @param sortBy
     * @return
     */
    List<Film> getFilmsByDirectors(String directorId, String sortBy);

    /**
     * Поиск по названию фильмов и по режиссёру
     *
     * @param query
     * @param by
     * @return List
     */
    List<Film> search(String query, String by);
}
