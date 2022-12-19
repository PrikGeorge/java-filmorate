package ru.yandex.practicum.filmorate.storage.mpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.mapper.MpaMapper;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;
import java.util.Optional;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@Repository
public class MpaDbStorage implements MpaStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MpaDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Mpa> getAll() {
        String sql = "SELECT m.id, m.name " +
                "FROM MPA_ratings AS m;";

        return jdbcTemplate.query(sql, new MpaMapper());
    }

    @Override
    public Optional<Mpa> findById(Long id) {
        String sqlQuery = "SELECT id, name " +
                "FROM MPA_ratings " +
                "WHERE id = ?;";

        List<Mpa> res = jdbcTemplate.query(sqlQuery, new MpaMapper(), id);

        return !res.isEmpty() ? Optional.of(res.get(0)) : Optional.empty();
    }

}
