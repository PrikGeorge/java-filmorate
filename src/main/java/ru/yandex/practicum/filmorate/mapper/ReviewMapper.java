package ru.yandex.practicum.filmorate.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
        return Review.builder()
                .reviewId(rs.getLong(MapperConstants.ID.lowerCaseName()))
                .filmId(rs.getLong(MapperConstants.FILM_ID.lowerCaseName()))
                .userId(rs.getLong(MapperConstants.USER_ID.lowerCaseName()))
                .content(rs.getString(MapperConstants.CONTENT.lowerCaseName()))
                .isPositive(rs.getBoolean(MapperConstants.ISPOSITIVE.lowerCaseName()))
                .useful(rs.getInt(MapperConstants.USEFUL.lowerCaseName()))
                .build();
    }
}
