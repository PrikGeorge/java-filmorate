package ru.yandex.practicum.filmorate.controller.review;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Review;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/reviews")
public interface ReviewController {

    /**
     * Добавление нового отзыва.
     *
     * @param review
     * @return
     */
    @PostMapping
    Review create(@Valid @RequestBody Review review);

    /**
     * Редактирование уже имеющегося отзыва.
     *
     * @param review
     * @return Review
     */
    @PutMapping
    Review update(@Valid @RequestBody Review review);

    /**
     * Удаление отзыва
     *
     * @param id
     * @return boolean
     */
    @DeleteMapping("/{id}")
    boolean deleteReview(@PathVariable(value = "id") Long id);

    /**
     * Получение отзыва по идентификатору
     *
     * @param id
     * @return Review
     */
    @GetMapping("/{id}")
    Review findById(@PathVariable(value = "id") Long id);

    /**
     * Получение всех отзывов по идентификатору фильма, если фильм не указан, то все. Если кол-во не указано то 10.
     *
     * @param filmId
     * @param count
     * @return List
     */
    @GetMapping
    List<Review> getReviews(@RequestParam(required = false) Long filmId, @RequestParam(defaultValue = "10", required = false) Integer count);

    /**
     * Пользователь ставит лайк отзыву
     *
     * @param id
     * @param userId
     * @return boolean
     */
    @PutMapping("/{id}/like/{userId}")
    boolean addLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId);

    /**
     * Пользователь ставит дизлайк отзыву
     *
     * @param id
     * @param userId
     * @return boolean
     */
    @PutMapping("/{id}/dislike/{userId}")
    boolean addDislike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId);

    /**
     * Пользователь удаляет лайк отзыву
     *
     * @param id
     * @param userId
     * @return boolean
     */
    @DeleteMapping("/{id}/like/{userId}")
    boolean removeLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId);

    /**
     * Пользователь удаляет дизлайк отзыву
     *
     * @param id
     * @param userId
     * @return boolean
     */
    @DeleteMapping("/{id}/dislike/{userId}")
    boolean removeDislike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId);
}
