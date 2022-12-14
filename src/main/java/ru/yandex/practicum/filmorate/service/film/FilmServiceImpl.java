package ru.yandex.practicum.filmorate.service.film;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 10.11.2022
 */

@Slf4j
@Service
public class FilmServiceImpl implements FilmService {

    public static final Comparator<Film> COMPARATOR_LIKES = (curFilm, nextFilm) -> nextFilm.getLikes().size() - curFilm.getLikes().size();
    private final FilmStorage storage;
    private final UserService userService;

    @Autowired
    public FilmServiceImpl(FilmStorage storage, UserService userService) {
        this.storage = storage;
        this.userService = userService;
    }

    @Override
    public boolean addLike(Long filmId, Long userId) {
        validateFilmId(filmId);
        userService.findById(userId);

        return storage.addLike(filmId, userId);
    }

    @Override
    public boolean removeLike(Long filmId, Long userId) {
        validateFilmId(filmId);
        userService.findById(userId);

        return storage.removeLike(filmId, userId);
    }

    @Override
    public List<Film> getMostPopularFilms(Integer limit) {
        return storage.getMostPopularFilms(Objects.requireNonNullElse(limit, 10));
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

    private Film validateFilmId(Long id) {
        return storage.findById(id).orElseThrow(() -> {
            log.info("Ошибка при валидации фильма.");
            throw new EntityNotFoundException("Фильм с id=" + id + " не найден");
        });
    }

}
