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
            String sqlQuery = "INSERT INTO REVIEWS (film_id, user_id, content, ispositive, useful) " +
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
        String sqlQuery = "UPDATE REVIEWS SET  content = ?, ispositive = ? " +
                "WHERE id = ?";

        int result = jdbcTemplate.update(sqlQuery, review.getContent(), review.getIsPositive(),
                review.getReviewId());
        if (result == 0) {
            return Optional.empty();
        }  else {
            Optional<Review> dbReview = findById(review.getReviewId());
            return dbReview;
        }
    }

    @Override
    public Optional<Review> findById(Long id) {
        String sql = "SELECT * FROM REVIEWS WHERE id = ?";
        List<Review> reviews = jdbcTemplate.query(sql, new ReviewMapper(), id);

        if (!reviews.isEmpty()) {
            Review review = reviews.get(0);
            return Optional.of(review);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteReview(Long id) {
        String sqlQuery = "DELETE FROM REVIEWS " +
                "WHERE id = ?";

        return jdbcTemplate.update(sqlQuery, id) != 0;
    }

    @Override
    public List<Review> getByFilmId(Long filmId, Integer count) {
        String sqlQuery = "SELECT id, film_id, user_id, content, ispositive, useful " +
                "FROM REVIEWS WHERE film_id = ? or ? IS NULL ORDER BY useful DESC LIMIT ?";

        return jdbcTemplate.query(sqlQuery, new ReviewMapper(), filmId, filmId, count);
    }

    @Override
    public boolean addLike(Long id, Long userId) {
        String sqlQuery = "INSERT INTO REVIEW_LIKES (review_id, user_id, ispositive) " +
                "VALUES (?, ?, true)";

        jdbcTemplate.update(sqlQuery, id, userId);

        String sqlQueryUpd = "UPDATE REVIEWS SET useful = useful + 1 " +
                "WHERE id = ?";
        return jdbcTemplate.update(sqlQueryUpd, id) != 0;
    }

    @Override
    public boolean addDisLike(Long id, Long userId) {
        String sqlQuery = "INSERT INTO REVIEW_LIKES (review_id, user_id, ispositive) " +
                "VALUES (?, ?, false)";

        jdbcTemplate.update(sqlQuery, id, userId);

        String sqlQueryUpd = "UPDATE REVIEWS SET useful = useful - 1 " +
                "WHERE id = ?";
        return jdbcTemplate.update(sqlQueryUpd, id) != 0;
    }

    @Override
    public boolean removeLike(Long id, Long userId) {
        String sqlQuery = "DELETE FROM REVIEW_LIKES " +
                "WHERE review_id = ? and user_id = ? and ispositive = true";

        int result = jdbcTemplate.update(sqlQuery, id, userId);

        if (result != 0) {
            String sqlQueryUpd = "UPDATE REVIEWS SET useful = useful - 1 " +
                    "WHERE id = ?";
            return jdbcTemplate.update(sqlQueryUpd, id) != 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeDisLike(Long id, Long userId) {
        String sqlQuery = "DELETE FROM REVIEW_LIKES " +
                "WHERE review_id = ? and user_id = ? and ispositive = false";

        int result = jdbcTemplate.update(sqlQuery, id, userId);

        if (result != 0) {
            String sqlQueryUpd = "UPDATE REVIEWS SET useful = useful + 1 " +
                    "WHERE id = ?";
            return jdbcTemplate.update(sqlQueryUpd, id) != 0;
        } else {
            return false;
        }
    }
}
