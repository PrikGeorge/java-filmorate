package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong(MapperConstants.ID.name()))
                .email(rs.getString(MapperConstants.EMAIL.name()))
                .login(rs.getString(MapperConstants.LOGIN.name()))
                .name(rs.getString(MapperConstants.NAME.name()))
                .birthday(Objects.nonNull(rs.getDate(MapperConstants.BIRTHDAY.name())) ?
                        rs.getDate(MapperConstants.BIRTHDAY.name()).toLocalDate() : null)
                .build();
    }
}
