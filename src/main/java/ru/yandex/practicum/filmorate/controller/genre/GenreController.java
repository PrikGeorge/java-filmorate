package ru.yandex.practicum.filmorate.controller.genre;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.yandex.practicum.filmorate.controller.Controllers;
import ru.yandex.practicum.filmorate.model.Genre;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@RequestMapping("/genres")
public interface GenreController extends Controllers<Genre> {
}
