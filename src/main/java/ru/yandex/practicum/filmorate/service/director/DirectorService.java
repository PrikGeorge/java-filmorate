package ru.yandex.practicum.filmorate.service.director;

import ru.yandex.practicum.filmorate.model.Director;

import java.util.List;
import java.util.Optional;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 20.12.2022
 */

public interface DirectorService {

    Director findById(Long id);

    List<Director> getAll();

    Director update(Director director);

    Director create(Director director);

    void remove(Long id);

}
