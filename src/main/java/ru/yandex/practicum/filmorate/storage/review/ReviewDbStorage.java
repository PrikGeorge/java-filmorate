package ru.yandex.practicum.filmorate.storage.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.mapper.MapperConstants;
import ru.yandex.practicum.filmorate.mapper.ReviewMapper;
import ru.yandex.practicum.filmorate.model.Review;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ReviewDbStorage implements ReviewStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Review create(Review review) {
        String sqlQuery = "INSERT INTO REVIEWS (film_id, user_id, content, is_positive, useful) " +
                "VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{MapperConstants.ID.lowerCaseName()});
            stmt.setLong(1, review.getFilmId());
            stmt.setLong(2, review.getUserId());
            stmt.setString(3, review.getContent());
            stmt.setBoolean(4, review.getIsPositive());
            stmt.setInt(5, 0);

            return stmt;
        }, keyHolder);

        review.setReviewId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return review;
    }

    @Override
    public Optional<Review> update(Review review) {
        String qs = "UPDATE REVIEWS SET  content = ?, is_positive = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(qs, review.getContent(), review.getIsPositive(),
                review.getReviewId());

        return findById(review.getReviewId());
    }

    @Override
    public Optional<Review> findById(Long id) {
        String sqlQuery = "SELECT * FROM reviews WHERE id = ?";
        List<Review> reviews = jdbcTemplate.query(sqlQuery, new ReviewMapper(), id);
        return reviews.size() > 0 ? Optional.of(reviews.get(0)) : Optional.empty();
    }

    @Override
    public boolean deleteReview(Long id) {
        String sqlQuery = "DELETE FROM REVIEWS " +
                "WHERE id = ?";

        return jdbcTemplate.update(sqlQuery, id) != 0;
    }

    @Override
    public List<Review> getReviews(Long filmId, Integer count) {
        String qs;
        List<Review> listReview;
        if (filmId == null) {
            qs = "SELECT * FROM reviews ORDER BY useful DESC LIMIT ?";
            listReview = jdbcTemplate.query(qs, new ReviewMapper(), count);
        } else {
            qs = "SELECT * FROM reviews where film_id = ? ORDER BY useful DESC LIMIT ?";
            listReview = jdbcTemplate.query(qs, new ReviewMapper(), filmId, count);
        }
        return listReview;
    }

    @Override
    public boolean addLike(Long id, Long userId) {
        String sqlQuery = "INSERT INTO REVIEWS_LIKES (review_id, user_id, is_positive) " +
                "VALUES (?, ?, true)";

        jdbcTemplate.update(sqlQuery, id, userId);

        String sqlQueryUpd = "UPDATE REVIEWS SET useful = useful + 1  WHERE id = ?";
        return jdbcTemplate.update(sqlQueryUpd, id) != 0;
    }

    @Override
    public boolean addDislike(Long id, Long userId) {
        String sqlQuery = "INSERT INTO REVIEWS_LIKES (review_id, user_id, is_positive) " +
                "VALUES (?, ?, false)";

        jdbcTemplate.update(sqlQuery, id, userId);

        String sqlQueryUpd = "UPDATE REVIEWS SET useful = useful - 1  WHERE id = ?";
        return jdbcTemplate.update(sqlQueryUpd, id) != 0;
    }

    @Override
    public boolean removeLike(Long id, Long userId) {
        String sqlQuery = "DELETE FROM REVIEWS_LIKES " +
                "WHERE review_id = ? and user_id = ? and is_positive = true";

        if (jdbcTemplate.update(sqlQuery, id, userId) == 0) {
            return false;
        }
        String sqlQueryUpd = "UPDATE REVIEWS SET useful = useful - 1  WHERE id = ?";
        return jdbcTemplate.update(sqlQueryUpd, id) != 0;
    }

    @Override
    public boolean removeDislike(Long id, Long userId) {
        String sqlQuery = "DELETE FROM REVIEWS_LIKES  WHERE review_id = ? " +
                "and user_id = ? and is_positive = false";

        if (jdbcTemplate.update(sqlQuery, id, userId) == 0) {
            return false;

        }
        String sqlQueryUpd = "UPDATE REVIEWS SET useful = useful + 1  WHERE id = ?";
        return jdbcTemplate.update(sqlQueryUpd, id) != 0;
    }
}
