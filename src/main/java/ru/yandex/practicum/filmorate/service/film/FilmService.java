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
    Film addLike(Long filmId, Long userId);

    /**
     * Удаление лайка пользователя у фильма
     *
     * @param filmId
     * @param userId
     * @return boolean
     */
    Film removeLike(Long filmId, Long userId);

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
     * Получение списка общих с другом фильмов, отсортированных по популярности
     *
     * @param userId
     * @param friendId
     * @return List
     */
    List<Film> getCommonFilms(Long userId, Long friendId);

    /**
     * Удаление фильма
     *
     * @param id
     * @return boolean
     */
    boolean remove(Long id);

    /**
     * Возвращает список фильмов режиссера отсортированных
     * по количеству лайков или году выпуска
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
