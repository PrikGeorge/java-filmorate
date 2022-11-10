package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.*;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 10.11.2022
 */

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Long, Film> films = new HashMap<>();

    @Override
    public Film create(Film film) {
        films.put(film.getId(), film);

        log.debug("Фильм успешно добавлен.");
        return film;
    }

    @Override
    public Film update(Film film) {
        Film curFilm = films.get(film.getId());
        curFilm.setName(film.getName());
        curFilm.setDuration(film.getDuration());
        curFilm.setDescription(film.getDescription());
        curFilm.setReleaseDate(film.getReleaseDate());

        log.debug("Данные фильма успешно обновлены.");
        return curFilm;
    }

    @Override
    public List<Film> getAll() {
        return new ArrayList<>(films.values());
    }

    @Override
    public Optional<Film> findById(Long id) {
        if (films.containsKey(id)) {
            return Optional.of(films.get(id));
        }

        return Optional.empty();
    }

}
