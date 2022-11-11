package ru.yandex.practicum.filmorate.controller.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;

import javax.validation.Valid;
import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 31.10.2022
 */

@RestController
public class FilmController implements FilmOperations {

    private final FilmService service;

    @Autowired
    public FilmController(FilmService service) {
        this.service = service;
    }

    /**
     * Создание фильма
     *
     * @param film
     * @return Film
     */
    @Override
    public Film create(@Valid @RequestBody Film film) {
        return service.create(film);
    }

    /**
     * Обновление объекта
     *
     * @param film
     * @return Film
     */
    @Override
    public Film update(@Valid @RequestBody Film film) {
        return service.update(film);
    }

    /**
     * Получение списка всех фильмов
     *
     * @return List
     */
    @Override
    public List<Film> getAll() {
        return service.getAll();
    }

    /**
     * Получение фильма по id
     *
     * @param id
     * @return Film
     */
    @Override
    public Film findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    /**
     * Добавление лайка пользователя к фильму
     *
     * @param id
     * @param userId
     * @return boolean
     */
    @Override
    public boolean addLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return service.addLike(id, userId);
    }

    /**
     * Удаление лайка пользователя у фильма
     *
     * @param id
     * @param userId
     * @return boolean
     */
    @Override
    public boolean removeLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return service.removeLike(id, userId);
    }

    /**
     * Получение списка популярных фильмов
     *
     * @param count количество фильмов, которое нужно вернуть
     * @return List
     */
    @Override
    public List<Film> getPopular(@RequestParam(required = false) Integer count) {
        return service.getMostPopular(count);
    }

}
