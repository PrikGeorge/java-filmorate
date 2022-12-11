package ru.yandex.practicum.filmorate.controller.genre;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.service.genre.GenreService;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@RestController
public class GenreControllerImpl implements GenreController {

    private final GenreService service;

    public GenreControllerImpl(GenreService service) {
        this.service = service;
    }

    @Override
    public List<Genre> getAll() {
        return service.getAll();
    }

    @Override
    public Genre findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
