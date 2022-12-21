package ru.yandex.practicum.filmorate.controller.director;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.yandex.practicum.filmorate.controller.Controllers;
import ru.yandex.practicum.filmorate.model.Director;
import ru.yandex.practicum.filmorate.model.Film;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 20.12.2022
 */

@RequestMapping("/directors")
public interface DirectorController  extends Controllers<Director> {
}
