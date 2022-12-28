package ru.yandex.practicum.filmorate.controller.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Review;
import ru.yandex.practicum.filmorate.service.review.ReviewService;

import java.util.List;
import java.util.Objects;

@RestController
public class ReviewControllerImpl implements ReviewController {

    private final ReviewService service;

    @Autowired
    public ReviewControllerImpl(ReviewService service) {
        this.service = service;
    }

    @Override
    public Review create(Review review) {
        return service.create(review);
    }

    @Override
    public Review update(Review review) {
        return service.update(review);
    }

    @Override
    public Review findById(Long id) {
        return service.findById(id);
    }

    @Override
    public boolean deleteReview(Long id) {
        return Objects.nonNull(service.deleteReview(id));
    }

    @Override
    public List<Review> getReviews(Long filmId, Integer count) {
        return service.getReviews(filmId, count);
    }

    @Override
    public boolean addLike(Long id, Long userId) {
        return service.addLike(id, userId);
    }

    @Override
    public boolean addDislike(Long id, Long userId) {
        return service.addDislike(id, userId);
    }

    @Override
    public boolean removeLike(Long id, Long userId) {
        return service.removeLike(id, userId);
    }

    @Override
    public boolean removeDislike(Long id, Long userId) {
        return service.removeDislike(id, userId);
    }
}
