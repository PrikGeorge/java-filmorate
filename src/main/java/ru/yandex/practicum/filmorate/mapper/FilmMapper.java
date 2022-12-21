package ru.yandex.practicum.filmorate.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.yandex.practicum.filmorate.model.Director;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */
public class FilmMapper implements ResultSetExtractor<List<Film>> {
    @Override
    public List<Film> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Film> films = new HashMap<>();

        while (rs.next()) {
            long id = rs.getLong(MapperConstants.ID.lowerCaseName());

            films.putIfAbsent(id, Film.builder()
                    .id(rs.getLong(MapperConstants.ID.lowerCaseName()))
                    .name(rs.getString(MapperConstants.NAME.lowerCaseName()))
                    .description(rs.getString(MapperConstants.DESCRIPTION.lowerCaseName()))
                    .releaseDate(rs.getDate(MapperConstants.RELEASE_DATE.lowerCaseName()).toLocalDate())
                    .duration(rs.getInt(MapperConstants.DURATION.lowerCaseName()))
                    .mpa(Mpa.builder()
                            .id(rs.getLong(MapperConstants.MPA_ID.lowerCaseName()))
                            .name(rs.getString(MapperConstants.MPA_NAME.lowerCaseName()))
                            .build())
                    .genres(new ArrayList<>())
                    .directors(new ArrayList<>())
                    .build()

            );

            long genreId = rs.getLong(MapperConstants.GENRE_ID.lowerCaseName());
            if (genreId != 0) {
                films.get(id).getGenres().add(
                        Genre.builder()
                        .id(genreId)
                        .name(rs.getString(MapperConstants.GENRE_NAME.lowerCaseName()))
                        .build()
                );
            }

            long directorId = rs.getLong(MapperConstants.DIRECTOR_ID.lowerCaseName());
            if (directorId != 0) {
                films.get(id).getDirectors().add(
                        Director.builder()
                        .id(directorId)
                        .name(rs.getString(MapperConstants.DIRECTOR_NAME.lowerCaseName()))
                        .build()
                );
            }


        }

        return new ArrayList<>(films.values());
    }
}
