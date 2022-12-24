
package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.mapper.MapperConstants;
import ru.yandex.practicum.filmorate.mapper.UserMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@Repository
@Slf4j
public class UserDbStorage implements UserStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAll() {
        String sqlQuery = "SELECT id, email, login, name, birthday " +
                "FROM users";

        return jdbcTemplate.query(sqlQuery, new UserMapper());
    }

    @Override
    public User create(User user) {
        String sqlQuery = "INSERT INTO USERS (name, login, email, birthday) " +
                "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{MapperConstants.ID.lowerCaseName()});
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getEmail());

            final LocalDate birthday = user.getBirthday();
            if (birthday == null) {
                stmt.setNull(4, Types.DATE);
            } else {
                stmt.setDate(4, Date.valueOf(birthday));
            }
            return stmt;
        }, keyHolder);

        user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return user;
    }

    @Override
    public Optional<User> update(User user) {
        String sqlQuery = "UPDATE users SET name = ?, login = ?, email = ?, birthday = ? " +
                "WHERE id = ?";

        return jdbcTemplate.update(sqlQuery, user.getName(),
                user.getLogin(), user.getEmail(), user.getBirthday(), user.getId()) == 0 ? Optional.empty() : Optional.of(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, new UserMapper(), id);

        if (!users.isEmpty()) {
            User user = users.get(0);
            user.setFriends(getFriendsByUserId(user.getId()));
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public boolean addFriend(Long id, Long friendId) {
        String sql = "INSERT INTO user_friends (user_id, friend_id, status) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, id, friendId, true) != 0;
    }

    @Override
    public boolean deleteFriend(Long id, Long friendId) {
        String sqlQuery = "DELETE FROM user_friends " +
                "WHERE (user_id = ? AND friend_id = ?)" +
                "OR (user_id = ? AND friend_id = ?)";

        return jdbcTemplate.update(sqlQuery, id, friendId, friendId, id) != 0;
    }

    @Override
    public List<User> getMutualFriends(Long id, Long friendId) {
        String sql = "SELECT  u.* " +
                "FROM user_friends AS fs " +
                "JOIN users AS u ON fs.friend_id = u.id " +
                "WHERE fs.user_id = ? AND fs.friend_id IN (" +
                "SELECT friend_id FROM user_friends WHERE user_id = ?)";

        return jdbcTemplate.query(sql, new UserMapper(), id, friendId);
    }

    @Override
    public List<User> getFriends(Long id) {
        String sql = "SELECT f.friend_id AS id,u.login,u.name,u.email,u.birthday " +
                "FROM user_friends AS f " +
                "LEFT JOIN users AS u ON u.id = f.friend_id " +
                "WHERE f.user_id = ?";

        return jdbcTemplate.query(sql, new UserMapper(), id);
    }

    private Set<Long> getFriendsByUserId(long id) {
        String sql = "select friend_id from user_friends where user_id=?";
        return new HashSet<>(
                jdbcTemplate.query(sql, (rs, num) -> rs.getLong("friend_id"), id)
        );
    }

    @Override
    public List<Film> getRecommendation(Long id) {
        List<User> users = getAll().stream().filter(user -> !Objects.equals(user.getId(), id)).collect(Collectors.toList());
        log.info("users" + users);
        String sql = "SELECT f.id " +
                "FROM films f " +
                "INNER JOIN films_likes ul on ul.film_id = f.id and ul.user_id = ? " +
                "INNER JOIN films_likes fl on fl.film_id = f.id and fl.user_id = ? ";
        AtomicInteger max = new AtomicInteger(0);
        AtomicLong userWithCommonFilms = new AtomicLong();
        users.forEach(user -> {
            List<Long> commonFilms = jdbcTemplate.query(sql, (rs, num) -> rs.getLong("id"), id, user.getId());
            if (commonFilms.size() > max.get()) {
                max.set(commonFilms.size());
                userWithCommonFilms.set(user.getId());
            }
        });
        log.info("max "+max.get()+" user "+userWithCommonFilms.get());
        List<Long> commonFilms = jdbcTemplate.query(sql, (rs, num) -> rs.getLong("id"), id, userWithCommonFilms.get());
        log.info("common " + commonFilms);
        String sqlQuery = "SELECT f.*, " +
                "m.name as mpa_name, " +
                "m.id as mpa_id, " +
                "g.id as genre_id, " +
                "g.name AS genre_name " +
                "FROM films as f " +
                "JOIN MPA_ratings as m ON f.mpa_id = m.id " +
                "LEFT JOIN films_genres AS fg ON f.id = fg.film_id " +
                "LEFT JOIN genres AS g ON fg.genre_id = g.id " +
                "WHERE f.id IN (" +
                "SELECT FILM_ID " +
                "FROM films_likes " +
                "WHERE user_id = ?)";
        List<Film> filmsWithoutCommons = new ArrayList<>();
        List<Film> otherUserFilms = jdbcTemplate.query(sqlQuery, new FilmMapper(), id);
        otherUserFilms.forEach(film -> {
            if(!commonFilms.contains(film.getId())) {
                filmsWithoutCommons.add(film);
            }
        });
        log.info("films with common "+otherUserFilms);
        return filmsWithoutCommons;
    }
}
