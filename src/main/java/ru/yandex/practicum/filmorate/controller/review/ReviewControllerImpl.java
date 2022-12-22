package ru.yandex.practicum.filmorate.controller.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Review;
import ru.yandex.practicum.filmorate.service.review.ReviewService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReviewControllerImpl implements ReviewController {

    private final ReviewService service;

    @Autowired
    public ReviewControllerImpl(ReviewService service) {
        this.service = service;
    }

    @Override
    public Review create(@Valid @RequestBody Review review) {
        return service.create(review);
    }

    @Override
    public Review update(@Valid @RequestBody Review review) {
        return service.update(review);
    }

    @Override
    public Review findById(@PathVariable(value = "id", required = false) Long id) {
        return service.findById(id);
    }

    @Override
    public boolean deleteReview(@PathVariable(value = "id") Long id) {
        return service.deleteReview(id);
    }


    @Override
    public List<Review> getByFilmId(@RequestParam(required = false) Long filmId, @RequestParam(defaultValue = "10", required = false) Integer count) {
        return service.getByFilmId(filmId, count);
    }


    @Override
    public boolean addLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return service.addLike(id, userId);
    }

    @Override
    public boolean addDisLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return service.addDisLike(id, userId);
    }

    @Override
    public boolean removeLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return service.removeLike(id, userId);
    }

    @Override
    public boolean removeDisLike(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return service.removeDisLike(id, userId);
    }
}
