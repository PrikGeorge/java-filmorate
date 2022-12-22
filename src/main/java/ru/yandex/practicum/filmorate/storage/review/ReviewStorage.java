package ru.yandex.practicum.filmorate.storage.review;

import ru.yandex.practicum.filmorate.model.Review;
import ru.yandex.practicum.filmorate.storage.Storage;

import java.util.List;

public interface ReviewStorage extends Storage<Review> {
    List<Review> getAll();

    boolean deleteReview(Long id);

    List<Review> getByFilmId(Long filmId, Integer count);

    boolean addLike(Long id, Long userId);

    boolean addDisLike(Long id, Long userId);

    boolean removeLike(Long id, Long userId);

    boolean removeDisLike(Long id, Long userId);
}
