package ru.yandex.practicum.filmorate.service.film;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.utils.GenerateIdentifier;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        Film film = validateFilmId(filmId);
        userService.findById(userId);

        return film.getLikes().add(userId);
    }

    @Override
    public boolean removeLike(Long filmId, Long userId) {
        Film film = validateFilmId(filmId);
        if (film.getLikes().size() == 0) {
            log.error("Ошибка при удалении лайка у фильма.");
            throw new EntityNotFoundException("У данного фильма нет лайков.");
        }

        return film.getLikes().remove(userId);
    }

    @Override
    public List<Film> getMostPopular(Integer limit) {
        List<Film> films = storage.getAll();
        films.sort(COMPARATOR_LIKES);

        return films.stream().limit(Objects.requireNonNullElse(limit, 10)).collect(Collectors.toList());
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
        return storage.update(film);
    }

    @Override
    public Film create(@NonNull Film film) {
        if (Objects.nonNull(film.getId())) {
            validateFilmId(film.getId());
        }

        film.setId(GenerateIdentifier.INSTANCE.generateId(Film.class));
        return storage.create(film);
    }

    private Film validateFilmId(Long id) {
        return storage.findById(id).orElseThrow(() -> {
            log.error("Ошибка при валидации фильма.");
            throw new EntityNotFoundException("Фильм с id=" + id + " не найден");
        });
    }

}
