package ru.yandex.practicum.filmorate.storage.review;

import ru.yandex.practicum.filmorate.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewStorage {

    Review create(Review review);

    Optional<Review> update(Review review);

    boolean deleteReview(Long id);

    Optional<Review> findById(Long id);

    List<Review> getByFilmId(Long filmId, Integer count);

    boolean addLike(Long id, Long userId);

    boolean addDisLike(Long id, Long userId);

    boolean removeLike(Long id, Long userId);

    boolean removeDisLike(Long id, Long userId);
}
