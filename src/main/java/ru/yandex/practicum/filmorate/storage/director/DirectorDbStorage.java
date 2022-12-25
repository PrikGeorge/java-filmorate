package ru.yandex.practicum.filmorate.storage.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.mapper.DirectorMapper;
import ru.yandex.practicum.filmorate.mapper.MapperConstants;
import ru.yandex.practicum.filmorate.model.Director;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 20.12.2022
 */

@Repository
public class DirectorDbStorage implements DirectorStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DirectorDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Director> findById(Long id) {
        String query = "SELECT id, name " +
                "FROM directors " +
                "WHERE id = ?;";

        List<Director> directors = jdbcTemplate.query(query, new DirectorMapper(), id);
        if (!directors.isEmpty()) {
            return Optional.of(directors.get(0));
        }

        return Optional.empty();
    }

    @Override
    public List<Director> getAll() {
        String query = "SELECT id, name " +
                "FROM directors " +
                "ORDER BY id ASC ";

        return jdbcTemplate.query(query, new DirectorMapper());
    }

    @Override
    public Optional<Director> update(Director director) {
        String query = "UPDATE directors SET name = ? WHERE id = ?";

        return jdbcTemplate.update(query, director.getName(), director.getId()) == 0 ? Optional.empty() : Optional.of(director);
    }

    @Override
    public Director create(Director director) {
        String query = "INSERT INTO directors (name) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(query, new String[]{MapperConstants.ID.lowerCaseName()});
            stmt.setString(1, director.getName());
            return stmt;
        }, keyHolder);

        director.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return director;
    }

    @Override
    public boolean remove(Long id) {
        return jdbcTemplate.update("DELETE FROM directors WHERE id=?", id) != 0;
    }

}
