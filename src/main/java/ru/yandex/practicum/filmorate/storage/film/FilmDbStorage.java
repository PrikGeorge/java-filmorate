package ru.yandex.practicum.filmorate.storage.film;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.mapper.MapperConstants;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@Repository
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Film> getAll() {
        String sqlQuery = "SELECT f.*, " +
                "m.name as mpa_name, " +
                "m.id as mpa_id, " +
                "g.id as genre_id, " +
                "g.name AS genre_name " +
                "FROM films as f " +
                "JOIN MPA_ratings as m ON f.mpa_id = m.id " +
                "LEFT JOIN films_genres AS fg ON f.id = fg.film_id " +
                "LEFT JOIN genres AS g ON fg.genre_id = g.id ";

        return jdbcTemplate.query(sqlQuery, new FilmMapper());
    }

    @Override
    public Film create(Film film) {
        String sqlQuery = "INSERT INTO films (name, description, release_date, " +
                "duration, mpa_id) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Film finalFilm = film;

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{MapperConstants.ID.lowerCaseName()});
            stmt.setString(1, finalFilm.getName());
            stmt.setString(2, finalFilm.getDescription());
            final LocalDate releaseDate = finalFilm.getReleaseDate();

            if (isNull(releaseDate)) {
                stmt.setNull(3, Types.DATE);
            } else {
                stmt.setDate(3, Date.valueOf(releaseDate));
            }

            stmt.setInt(4, finalFilm.getDuration());
            stmt.setLong(5, finalFilm.getMpa().getId());

            return stmt;
        }, keyHolder);
        film.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        Optional<Film> opFilm = updateFilmGenres(film);
        if (opFilm.isPresent()) {
            film = opFilm.get();
        }

        return film;
    }

    @Override
    public Optional<Film> update(Film film) {
        String sqlQuery = "UPDATE films SET name = ?, " +
                "description = ?, " +
                "release_date = ?, " +
                "duration = ?, " +
                "mpa_id = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sqlQuery, film.getName(), film.getDescription(), film.getReleaseDate(),
                film.getDuration(), film.getMpa().getId(), film.getId());
        return updateFilmGenres(film);
    }

    @Override
    public Optional<Film> findById(@NonNull Long id) {
        String sqlQuery = "SELECT f.*, " +
                "m.name AS mpa_name, " +
                "m.id AS mpa_id, " +
                "g.id as genre_id, " +
                "g.name AS genre_name " +
                "FROM films AS f " +
                "JOIN MPA_ratings AS m ON f.mpa_id = m.id " +
                "LEFT JOIN films_genres AS fg ON f.id = fg.film_id " +
                "LEFT JOIN genres AS g ON fg.genre_id = g.id " +
                "WHERE f.id = ?";

        List<Film> res = jdbcTemplate.query(sqlQuery, new FilmMapper(), id);

        if (Objects.nonNull(res) && !res.isEmpty()) {
            return Optional.of(res.get(0));
        }
        return Optional.empty();
    }

    private Optional<Film> updateFilmGenres(Film film) {
        jdbcTemplate.update("DELETE FROM films_genres WHERE film_id = ?", film.getId());

        if (!isNull(film.getGenres()) && !film.getGenres().isEmpty()) {

            String sql = "INSERT INTO films_genres (genre_id, film_id) VALUES (?, ?)";
            List<Long> genres = film.getGenres().stream().map(Genre::getId).distinct().collect(Collectors.toList());
            jdbcTemplate.batchUpdate(sql, genres, 50,
                    (PreparedStatement ps, Long genreId) -> {
                        ps.setLong(1, genreId);
                        ps.setLong(2, film.getId());
                    });
        }
        return findById(film.getId());
    }

    @Override
    public List<Film> getMostPopularFilms(int limit) {
        String sql = "SELECT *, " +
                "m.id AS mpa_id, " +
                "m.name AS mpa_name, " +
                "g.id as genre_id, " +
                "g.name AS genre_name " +
                "FROM films f " +
                "LEFT JOIN " +
                "(SELECT film_id, " +
                "COUNT(*) likes_count " +
                "FROM films_likes " +
                "GROUP BY film_id) " +
                "l ON f.id = l.film_id " +
                "LEFT JOIN MPA_ratings AS m ON f.mpa_id = m.id " +
                "LEFT JOIN films_genres AS fg ON f.id = fg.film_id " +
                "LEFT JOIN genres AS g ON fg.genre_id = g.id " +
                "ORDER BY l.likes_count DESC LIMIT ?";

        return jdbcTemplate.query(sql, new FilmMapper(), limit);
    }

    @Override
    public boolean addLike(Long filmId, Long userId) {
        String sql = "INSERT INTO films_likes (film_id, user_id) VALUES (?, ?)";

        return jdbcTemplate.update(sql, filmId, userId) != 0;
    }

    @Override
    public boolean removeLike(Long filmId, Long userId) {
        String sql = "DELETE FROM films_likes WHERE film_id=? AND user_id=?";

        return jdbcTemplate.update(sql, filmId, userId) != 0;
    }

}
