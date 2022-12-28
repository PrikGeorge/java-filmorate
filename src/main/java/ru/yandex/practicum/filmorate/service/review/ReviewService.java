package ru.yandex.practicum.filmorate.service.review;

import ru.yandex.practicum.filmorate.model.Review;

import java.util.List;

public interface ReviewService {

    /**
     * Добавление нового отзыва.
     *
     * @param review
     * @return
     */
    Review create(Review review);

    /**
     * Редактирование уже имеющегося отзыва.
     *
     * @param review
     * @return Review
     */
    Review update(Review review);

    /**
     * Получение отзыва по идентификатору
     *
     * @param id
     * @return Review
     */
    Review findById(Long id);

    /**
     * Удаление отзыва
     *
     * @param id
     * @return boolean
     */
    Review deleteReview(Long id);

    /**
     * Получение всех отзывов по идентификатору фильма, если фильм не указан, то все. Если кол-во не указано то 10.
     *
     * @param filmId
     * @param count
     * @return List
     */
    List<Review> getReviews(Long filmId, Integer count);

    /**
     * Пользователь ставит лайк отзыву
     *
     * @param id
     * @param userId
     * @return boolean
     */
    boolean addLike(Long id, Long userId);

    /**
     * Пользователь ставит дизлайк отзыву
     *
     * @param id
     * @param userId
     * @return boolean
     */
    boolean addDislike(Long id, Long userId);

    /**
     * Пользователь удаляет лайк отзыву
     *
     * @param id
     * @param userId
     * @return boolean
     */
    boolean removeLike(Long id, Long userId);

    /**
     * Пользователь удаляет дизлайк отзыву
     *
     * @param id
     * @param userId
     * @return boolean
     */
    boolean removeDislike(Long id, Long userId);
}
