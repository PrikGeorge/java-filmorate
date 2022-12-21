package ru.yandex.practicum.filmorate.service.director;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.Director;
import ru.yandex.practicum.filmorate.storage.director.DirectorDbStorage;
import ru.yandex.practicum.filmorate.storage.director.DirectorStorage;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 20.12.2022
 */

@Slf4j
@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorStorage storage;

    @Autowired
    public DirectorServiceImpl(DirectorStorage storage) {
        this.storage = storage;
    }

    @Override
    public Director findById(Long id) {
        return storage.findById(id).orElseThrow(() -> {
            log.info("Ошибка при валидации Режисера.");
            throw new EntityNotFoundException("Режисер с id=" + id + " не найден.");
        });
    }

    @Override
    public List<Director> getAll() {
        return storage.getAll();
    }

    @Override
    public Director update(Director director) {
        findById(director.getId());
        return storage.update(director).orElseThrow(() -> {
            log.info("Ошибка при обновлении режисера.");
            throw new EntityNotFoundException("Ошибка при обновлении режисера с id=" + director.getId());
        });
    }

    @Override
    public Director create(Director director) {
        return storage.create(director);
    }

    @Override
    public void remove(Long id) {
        storage.remove(id);
    }
}
