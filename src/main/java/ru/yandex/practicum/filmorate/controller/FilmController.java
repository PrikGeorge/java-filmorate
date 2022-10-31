package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.utils.GenerateIdentifier;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final Map<Long, Film> films = new HashMap<>();

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        film.setId(GenerateIdentifier.INSTANCE.generateId(Film.class));
        films.put(film.getId(), film);

        log.debug("Фильм успешно добавлен.");
        return film;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        Film curFilm = films.get(film.getId());

        if (Objects.nonNull(curFilm)) {
            curFilm.setName(film.getName());
            curFilm.setDuration(film.getDuration());
            curFilm.setDescription(film.getDescription());
            curFilm.setReleaseDate(film.getReleaseDate());

            log.debug("Данные фильма успешно обновлены.");
            return curFilm;
        }

        throw new EntityNotFoundException("Фильм не найден.");
    }

    @GetMapping
    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }
}
