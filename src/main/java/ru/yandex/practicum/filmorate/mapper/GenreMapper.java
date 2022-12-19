package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */
public class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Genre.builder()
                .id(rs.getLong(MapperConstants.ID.lowerCaseName()))
                .name(rs.getString(MapperConstants.NAME.lowerCaseName()))
                .build();
    }
}
