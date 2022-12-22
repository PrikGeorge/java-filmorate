package ru.yandex.practicum.filmorate.service.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.Review;
import ru.yandex.practicum.filmorate.service.film.FilmService;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.storage.review.ReviewStorage;

import java.util.List;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewStorage storage;
    private final UserService userService;
    private final FilmService filmService;

    @Autowired
    public ReviewServiceImpl(ReviewStorage storage, UserService userService, FilmService filmService) {
        this.storage = storage;
        this.userService = userService;
        this.filmService = filmService;
    }


    @Override
    public Review create(Review review) {
        userService.findById(review.getUserId());
        filmService.findById(review.getFilmId());
        return storage.create(review);
    }

    @Override
    public Review update(Review review) {
        userService.findById(review.getUserId());
        filmService.findById(review.getFilmId());
        return storage.update(review).orElseThrow(() -> {
            log.info("Ошибка при обновлении отзыва.");
            throw new EntityNotFoundException("Ошибка при обновлении отзыва с id=" + review.getReviewId());
        });
    }

    @Override
    public Review findById(Long id) {
        return validateReviewId(id);
    }

    @Override
    public boolean deleteReview(Long id) {
        validateReviewId(id);
        return storage.deleteReview(id);
    }

    @Override
    public List<Review> getByFilmId(Long filmId, Integer count) {
        return storage.getByFilmId(filmId, count);
    }

    @Override
    public boolean addLike(Long id, Long userId) {
        validateReviewId(id);
        userService.findById(userId);
        return storage.addLike(id, userId);
    }

    @Override
    public boolean addDisLike(Long id, Long userId) {
        validateReviewId(id);
        userService.findById(userId);
        return storage.addDisLike(id, userId);
    }

    @Override
    public boolean removeLike(Long id, Long userId) {
        validateReviewId(id);
        userService.findById(userId);
        boolean result = storage.removeLike(id, userId);
        if (result) {
            return result;
        } else {
            log.info("Ошибка при удалении лайка с отзыва.");
            throw new EntityNotFoundException("Ошибка при удалении лайка с отзыва id=" + id);
        }
    }

    @Override
    public boolean removeDisLike(Long id, Long userId) {
        validateReviewId(id);
        userService.findById(userId);
        boolean result = storage.removeDisLike(id, userId);
        if (result) {
            return result;
        } else {
            log.info("Ошибка при удалении дизлайка с отзыва.");
            throw new EntityNotFoundException("Ошибка при удалении дизлайка с отзыва id=" + id);
        }
    }

    private Review validateReviewId(Long id) {
        return storage.findById(id).orElseThrow(() -> {
            log.info("Ошибка при валидации отзыва.");
            throw new EntityNotFoundException("Отзыв с id=" + id + " не найден.");
        });
    }
}
