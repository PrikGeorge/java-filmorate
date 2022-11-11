package ru.yandex.practicum.filmorate.controller.film;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.controller.Operations;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 11.11.2022
 */

@RequestMapping("/films")
public interface FilmOperations extends Operations<Film> {

    @PutMapping("/{id}/like/{userId}")
    boolean addLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId);

    @DeleteMapping("/{id}/like/{userId}")
    boolean removeLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId);

    @GetMapping("/popular")
    List<Film> getPopular(@RequestParam(required = false) Integer count);

}
