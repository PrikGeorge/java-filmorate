package ru.yandex.practicum.filmorate.controller.mpa;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.yandex.practicum.filmorate.controller.Controllers;
import ru.yandex.practicum.filmorate.model.Mpa;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@RequestMapping("/mpa")
public interface MPAController extends Controllers<Mpa> {
}
