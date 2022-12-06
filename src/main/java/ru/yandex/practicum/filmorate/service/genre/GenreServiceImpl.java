package ru.yandex.practicum.filmorate.service.genre;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@Slf4j
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreStorage storage;

    public GenreServiceImpl(GenreStorage storage) {
        this.storage = storage;
    }

    @Override
    public List<Genre> getAll() {
        return storage.getAll();
    }

    @Override
    public Genre findById(Long id) {
        return storage.findById(id).orElseThrow(() -> {
            log.info("Ошибка при поиске жанра.");
            throw new EntityNotFoundException("Жанр с id=" + id + " не найден.");
        });
    }
}
