package ru.yandex.practicum.filmorate.storage.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.mapper.EventMapper;
import ru.yandex.practicum.filmorate.model.Event;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 28.12.2022
 */

@Repository
public class EventDbStorage implements EventStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EventDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> getEventsByUserId(Long id) {
        String sql = "SELECT * FROM events WHERE user_id=?";

        return jdbcTemplate.query(sql, new EventMapper(), id);
    }

    @Override
    public Event create(Event event) {
        String sqlQuery = "INSERT INTO events (user_id, entity_id, event_type, operation, timestamp) " +
                "values (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"event_id"});
            stmt.setLong(1, event.getUserId());
            stmt.setLong(2, event.getEntityId());
            stmt.setObject(3, event.getEventType().name());
            stmt.setObject(4, event.getOperation().name());
            stmt.setObject(5, Timestamp.valueOf(LocalDateTime.now()));
            return stmt;
        }, keyHolder);

        event.setEventId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return event;
    }

}
