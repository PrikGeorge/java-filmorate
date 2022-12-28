package ru.yandex.practicum.filmorate.service.user;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.aop.feed.AddEvent;
import ru.yandex.practicum.filmorate.aop.feed.RemoveEvent;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.Event;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.event.EventStorage;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 10.11.2022
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserStorage storage;
    private final FilmStorage filmStorage;

    private final EventStorage eventStorage;

    @Autowired
    public UserServiceImpl(UserStorage storage,
                           FilmStorage filmStorage,
                           EventStorage eventStorage) {
        this.storage = storage;
        this.filmStorage = filmStorage;
        this.eventStorage = eventStorage;
    }

    @Override
    @AddEvent
    public User addFriend(Long id, Long friendId) {
        User sourceUser = validateUserId(id);
        validateUserId(friendId);

        storage.addFriend(id, friendId);
        return sourceUser;
    }

    @Override
    @RemoveEvent
    public User deleteFriend(Long id, Long friendId) {
        User user = validateUserId(id);
        validateUserId(friendId);

        storage.deleteFriend(id, friendId);
        return user;
    }

    @Override
    public List<User> getMutualFriends(Long id, Long friendId) {
        User sourceUser = validateUserId(id);
        validateUserId(friendId);

        if (sourceUser.getFriends().isEmpty()) {
            return new ArrayList<>();
        }

        return storage.getMutualFriends(id, friendId);
    }

    @Override
    public List<User> getFriends(Long id) {
        validateUserId(id);
        return storage.getFriends(id);
    }

    @Override
    public User findById(Long id) {
        return validateUserId(id);
    }

    @Override
    public List<User> getAll() {
        return storage.getAll();
    }

    @Override
    public User update(@NonNull User user) {
        validateUserId(user.getId());
        return storage.update(user).orElseThrow(() -> {
            log.info("Ошибка при обновлении пользователя.");
            throw new EntityNotFoundException("Ошибка при обновлении пользователя с id=" + user.getId());
        });
    }

    @Override
    public User create(@NonNull User user) {
        if (Objects.nonNull(user.getId())) {
            validateUserId(user.getId());
        }

        if (Objects.isNull(user.getName()) || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        return storage.create(user);
    }

    @Override
    public boolean remove(@NonNull Long id) {
        validateUserId(id);
        return storage.remove(id);
    }

    private User validateUserId(Long id) {
        return storage.findById(id).orElseThrow(() -> {
            log.info("Ошибка при валидации пользователя.");
            throw new EntityNotFoundException("Пользователь с id=" + id + " не найден.");
        });
    }

    @Override
    public List<Film> getRecommendations(@NonNull Long id) {
        validateUserId(id);
        return storage.getRecommendation(id);
    }

    @Override
    public List<Event> getFeed(Long id) {
        validateUserId(id);
        return eventStorage.getEventsByUserId(id);
    }
}
