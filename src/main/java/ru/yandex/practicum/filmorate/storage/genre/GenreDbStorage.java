package ru.yandex.practicum.filmorate.storage.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.mapper.GenreMapper;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;
import java.util.Optional;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@Repository
public class GenreDbStorage implements GenreStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GenreDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> getAll() {
        String sql = "SELECT id, name " +
                "FROM genres " +
                "ORDER BY id ASC ";

        return jdbcTemplate.query(sql, new GenreMapper());
    }

    @Override
    public Optional<Genre> findById(Long id) {
        String sql = "SELECT g.id, " +
                "g.name " +
                "FROM genres AS g " +
                "WHERE g.id = ?;";

        List<Genre> res = jdbcTemplate.query(sql, new GenreMapper(), id);

        return !res.isEmpty() ? Optional.of(res.get(0)) : Optional.empty();
    }
}
