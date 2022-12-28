package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Event;
import ru.yandex.practicum.filmorate.model.EventType;
import ru.yandex.practicum.filmorate.model.Operation;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 28.12.2022
 */

public class EventMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Event.builder()
                .eventId(rs.getLong(MapperConstants.EVENT_ID.lowerCaseName()))
                .userId(rs.getLong(MapperConstants.USER_ID.lowerCaseName()))
                .entityId(rs.getLong(MapperConstants.ENTITY_ID.lowerCaseName()))
                .eventType(EventType.valueOf(rs.getString(MapperConstants.EVENT_TYPE.lowerCaseName())))
                .operation(Operation.valueOf(rs.getString(MapperConstants.OPERATION.lowerCaseName())))
                .timestamp(rs.getTimestamp(MapperConstants.TIMESTAMP.lowerCaseName()).getTime())
                .build();
    }

}
