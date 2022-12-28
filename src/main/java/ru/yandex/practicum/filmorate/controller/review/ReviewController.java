package ru.yandex.practicum.filmorate.controller.review;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Review;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/reviews")
public interface ReviewController {

    //Добавление нового отзыва.
    @PostMapping
    Review create(@Valid @RequestBody Review review);

    //Редактирование уже имеющегося отзыва.
    @PutMapping
    Review update(@Valid @RequestBody Review review);

    //Удаление отзыва
    @DeleteMapping("/{id}")
    boolean deleteReview(@PathVariable(value = "id") Long id);

    //Получение отзыва по идентификатору
    @GetMapping("/{id}")
    Review findById(@PathVariable(value = "id") Long id);

    //Получение всех отзывов по идентификатору фильма, если фильм не указан, то все. Если кол-во не указано то 10.
    @GetMapping
    List<Review> getReviews(@RequestParam(required = false) Long filmId, @RequestParam(defaultValue = "10", required = false) Integer count);

    //Пользователь ставит лайк отзыву
    @PutMapping("/{id}/like/{userId}")
    boolean addLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId);

    //Пользователь ставит дизлайк отзыву
    @PutMapping("/{id}/dislike/{userId}")
    boolean addDislike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId);

    //Пользователь удаляет лайк отзыву
    @DeleteMapping("/{id}/like/{userId}")
    boolean removeLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId);

    //Пользователь удаляет дизлайк отзыву
    @DeleteMapping("/{id}/dislike/{userId}")
    boolean removeDislike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId);
}
