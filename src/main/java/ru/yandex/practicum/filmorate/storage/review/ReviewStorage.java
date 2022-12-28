package ru.yandex.practicum.filmorate.storage.review;

import ru.yandex.practicum.filmorate.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewStorage {

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
    Optional<Review> update(Review review);

    /**
     * Удаление отзыва
     *
     * @param id
     * @return boolean
     */
    boolean deleteReview(Long id);

    /**
     * Получение отзыва по идентификатору
     *
     * @param id
     * @return Review
     */
    Optional<Review> findById(Long id);

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
