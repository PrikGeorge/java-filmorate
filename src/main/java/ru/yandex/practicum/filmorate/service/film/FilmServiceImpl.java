package ru.yandex.practicum.filmorate.service.film;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.aop.feed.AddEvent;
import ru.yandex.practicum.filmorate.aop.feed.RemoveEvent;
import ru.yandex.practicum.filmorate.exception.BadRequestException;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.List;
import java.util.Objects;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 10.11.2022
 */

@Slf4j
@Service
public class FilmServiceImpl implements FilmService {

    private static final int YEAR_FIRST_FILM = 1895;
    private final FilmStorage storage;
    private final UserService userService;

    @Autowired
    public FilmServiceImpl(FilmStorage storage, UserService userService) {
        this.storage = storage;
        this.userService = userService;
    }

    @Override
    @AddEvent
    public Film addLike(Long filmId, Long userId) {
        Film film = validateFilmId(filmId);
        userService.findById(userId);

        if (Objects.nonNull(storage.checkLike(filmId, userId))) {
            return film;
        }
        storage.addLike(filmId, userId);
        return film;
    }

    @Override
    @RemoveEvent
    public Film removeLike(Long filmId, Long userId) {
        Film film = validateFilmId(filmId);
        userService.findById(userId);

        storage.removeLike(filmId, userId);
        return film;
    }

    @Override
    public List<Film> getMostPopularFilms(Integer limit, Integer genreId, Integer year) {

        if (limit <= 0) {
            throw new BadRequestException("Количество записей должно быть больше 0");
        }

        if (Objects.nonNull(genreId) && genreId <= 0) {
            throw new BadRequestException("genreId должен быть больше 0");
        }

        if (Objects.nonNull(year) && year < YEAR_FIRST_FILM) {
            throw new BadRequestException("Год должен быть не раньше года создания кино " + YEAR_FIRST_FILM);
        }
        return storage.getMostPopularFilms(limit, genreId, year);
    }

    @Override
    public Film findById(Long id) {
        return validateFilmId(id);
    }

    @Override
    public List<Film> getAll() {
        return storage.getAll();
    }

    @Override
    public Film update(@NonNull Film film) {
        validateFilmId(film.getId());
        return storage.update(film).orElseThrow(() -> {
            log.info("Ошибка при обновлении фильма.");
            throw new EntityNotFoundException("Ошибка при обновлении фильма с id=" + film.getId());
        });
    }

    @Override
    public Film create(@NonNull Film film) {
        if (Objects.nonNull(film.getId())) {
            validateFilmId(film.getId());
        }
        return storage.create(film);
    }

    @Override
    public List<Film> getCommonFilms(Long userId, Long friendId) {
        userService.findById(userId);
        userService.findById(friendId);
        return storage.getCommonFilms(userId, friendId);
    }

    @Override
    public boolean remove(@NonNull Long id) {
        validateFilmId(id);
        return storage.remove(id);
    }

    private Film validateFilmId(Long id) {
        return storage.findById(id).orElseThrow(() -> {
            log.info("Ошибка при валидации фильма.");
            throw new EntityNotFoundException("Фильм с id=" + id + " не найден");
        });
    }

    @Override
    public List<Film> getFilmsByDirectors(String directorId, String sortBy) {
        List<Film> films = storage.getFilmsByDirectors(directorId, sortBy);
        if (films.isEmpty()) {
            log.info("Фильмы с таким режисером не найдены.");
            throw new EntityNotFoundException("Фильмы с таким режисером не найдены.");
        }

        return films;
    }

    @Override
    public List<Film> search(String query, String by) {
        return storage.search(query, by);
    }

}
