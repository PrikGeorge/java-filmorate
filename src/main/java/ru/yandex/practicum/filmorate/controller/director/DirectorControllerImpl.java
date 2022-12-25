package ru.yandex.practicum.filmorate.controller.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Director;
import ru.yandex.practicum.filmorate.service.director.DirectorService;

import javax.validation.Valid;
import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 20.12.2022
 */

@RestController
public class DirectorControllerImpl implements DirectorController {

    private final DirectorService service;

    @Autowired
    public DirectorControllerImpl(DirectorService service) {
        this.service = service;
    }

    @Override
    public Director create(@Valid @RequestBody Director director) {
        return service.create(director);
    }

    @Override
    public Director update(@Valid @RequestBody Director director) {
        return service.update(director);
    }

    @Override
    public List<Director> getAll() {
        return service.getAll();
    }

    @Override
    public Director findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @Override
    public boolean remove(@PathVariable(value = "id") Long id) {
        return service.remove(id);
    }

}
