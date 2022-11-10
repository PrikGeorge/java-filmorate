package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmServiceImpl;

import javax.validation.Valid;
import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 31.10.2022
 */

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmServiceImpl service;

    @Autowired
    public FilmController(FilmServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        return service.create(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        return service.update(film);
    }

    @GetMapping
    public List<Film> getFilms() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Film getFilm(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public boolean addLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return service.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public boolean removeLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return service.removeLike(id, userId);
    }

    @GetMapping("/popular")
    public List<Film> getPopular(@RequestParam(required = false) Integer count) {
        return service.getMostPopular(count);
    }

}
