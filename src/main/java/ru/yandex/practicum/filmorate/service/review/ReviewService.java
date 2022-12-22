package ru.yandex.practicum.filmorate.service.review;

import ru.yandex.practicum.filmorate.model.Review;

import java.util.List;

public interface ReviewService {
    Review create(Review review);

    Review update(Review review);

    Review findById(Long id);

    boolean deleteReview(Long id);

    List<Review> getByFilmId(Long filmId, Integer count);

    boolean addLike(Long id, Long userId);

    boolean addDisLike(Long id, Long userId);

    boolean removeLike(Long id, Long userId);

    boolean removeDisLike(Long id, Long userId);
}
