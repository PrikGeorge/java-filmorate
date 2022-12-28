package ru.yandex.practicum.filmorate.controller.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;

import java.util.List;
import java.util.Objects;

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
    public Film create(Film film) {
        return service.create(film);
    }

    @Override
    public Film update(Film film) {
        return service.update(film);
    }

    @Override
    public List<Film> getAll() {
        return service.getAll();
    }

    @Override
    public Film findById(Long id) {
        return service.findById(id);
    }

    @Override
    public boolean addLike(Long id, Long userId) {
        return Objects.nonNull(service.addLike(id, userId));
    }

    @Override
    public boolean removeLike(Long id, Long userId) {
        return Objects.nonNull(service.removeLike(id, userId));
    }

    @Override
    public List<Film> getPopular(Integer count, Integer genreId, Integer year) {
        return service.getMostPopularFilms(count, genreId, year);
    }

    @Override
    public List<Film> getCommonFilms(Long userId, Long friendId) {
        return service.getCommonFilms(userId, friendId);
    }

    @Override
    public boolean remove(Long id) {
        return service.remove(id);
    }

    @Override
    public List<Film> getFilmsByDirectors(String directorId, String sortBy) {
        return service.getFilmsByDirectors(directorId, sortBy);
    }

    @Override
    public List<Film> search(String query, String by) {
        return service.search(query, by);
    }

}
