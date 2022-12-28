package ru.yandex.practicum.filmorate.controller.film;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.controller.Controllers;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 11.11.2022
 */

@RequestMapping("/films")
public interface FilmController extends Controllers<Film> {

    /**
     * Добавление лайка пользователя к фильму
     *
     * @param id
     * @param userId
     * @return boolean
     */
    @PutMapping("/{id}/like/{userId}")
    boolean addLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId);

    /**
     * Удаление лайка пользователя у фильма
     *
     * @param id
     * @param userId
     * @return boolean
     */
    @DeleteMapping("/{id}/like/{userId}")
    boolean removeLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId);

    /**
     * Вывод самых популярных фильмов по жанру и годам
     *
     * @param count
     * @param genreId
     * @param year
     * @return List
     */
    @GetMapping("/popular")
    List<Film> getPopular(
            @RequestParam(required = false, defaultValue = "10") Integer count,
            @RequestParam(required = false) Integer genreId,
            @RequestParam(required = false) Integer year);

    /**
     * Получение списка общих с другом фильмов, отсортированных по популярности
     *
     * @param userId
     * @param friendId
     * @return List
     */
    @GetMapping("/common")
    List<Film> getCommonFilms(@RequestParam(value = "userId") Long userId, @RequestParam(value = "friendId") Long friendId);

    /**
     * Возвращает список фильмов режиссера отсортированных
     * по количеству лайков или году выпуска.
     *
     * @param directorId
     * @param sortBy
     * @return List
     */
    @GetMapping("/director/{directorId}")
    List<Film> getFilmsByDirectors(@PathVariable String directorId,
                                   @RequestParam(required = false, defaultValue = "year") String sortBy);

    /**
     * Поиск по названию фильмов и по режиссёру
     *
     * @param query
     * @param by
     * @return List
     */
    @GetMapping("/search")
    List<Film> search(@RequestParam() String query, @RequestParam() String by);

}
