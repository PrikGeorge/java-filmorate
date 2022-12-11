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
public class FilmControllerImpl implements FilmController {

    private final FilmService service;

    @Autowired
    public FilmControllerImpl(FilmService service) {
        this.service = service;
    }

    @Override
    public Film create(@Valid @RequestBody Film film) {
        return service.create(film);
    }

    @Override
    public Film update(@Valid @RequestBody Film film) {
        return service.update(film);
    }

    @Override
    public List<Film> getAll() {
        return service.getAll();
    }

    @Override
    public Film findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @Override
    public boolean addLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return service.addLike(id, userId);
    }

    @Override
    public boolean removeLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return service.removeLike(id, userId);
    }

    @Override
    public List<Film> getPopular(@RequestParam(required = false) Integer count) {
        return service.getMostPopularFilms(count);
    }

}
