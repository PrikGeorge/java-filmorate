package ru.yandex.practicum.filmorate.controller.mpa;

import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.mpa.MpaService;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@RestController
public class MPAControllerImpl implements MPAController {

    private final MpaService service;

    public MPAControllerImpl(MpaService service) {
        this.service = service;
    }

    @Override
    public List<Mpa> getAll() {
        return service.getAll();
    }

    @Override
    public Mpa findById(Long id) {
        return service.findById(id);
    }
}
