package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */
public class FilmMapper implements RowMapper<Film> {

    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Film.builder()
                .id(rs.getLong(MapperConstants.ID.name()))
                .name(rs.getString(MapperConstants.NAME.name()))
                .description(rs.getString(MapperConstants.DESCRIPTION.name()))
                .releaseDate(rs.getDate(MapperConstants.RELEASE_DATE.name()).toLocalDate())
                .duration(rs.getInt(MapperConstants.DURATION.name()))
                .mpa(Mpa.builder()
                        .id(rs.getLong(MapperConstants.MPA_ID.name()))
                        .name(rs.getString(MapperConstants.MPA_NAME.name()))
                        .build())
                .build();
    }
}
