package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 10.11.2022
 */

public interface FilmService {

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
     * Получение списка популярных фильмов
     *
     * @param limit количество фильмов, которое нужно вернуть
     * @return List
     */
    List<Film> getMostPopularFilms(Integer limit);

    /**
     * Поиск фильма по id
     *
     * @param id
     * @return Film
     */
    Film findById(Long id);

    /**
     * Поиск всех фильмов
     *
     * @return List
     */
    List<Film> getAll();

    /**
     * Обновление фильма
     *
     * @param film
     * @return Film
     */
    Film update(Film film);

    /**
     * Добавление фильма
     *
     * @param film
     * @return Film
     */
    Film create(Film film);

    /**
     * Удаление фильма
     *
     * @param id
     * @return boolean
     */
    boolean delete(Long id);
}
