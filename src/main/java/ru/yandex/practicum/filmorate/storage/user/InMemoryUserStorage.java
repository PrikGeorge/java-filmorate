package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 10.11.2022
 */

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {

    private final Map<Long, User> users = new HashMap<>();

    @Override
    public User create(User user) {
        users.put(user.getId(), user);

        log.debug("Пользователь успешно добавлен.");
        return user;
    }

    @Override
    public User update(User user) {
        User curUser = users.get(user.getId());
        curUser.setName(user.getName());
        curUser.setLogin(user.getLogin());
        curUser.setBirthday(user.getBirthday());
        curUser.setEmail(user.getEmail());

        log.debug("Данные пользователя успешно обновлены.");
        return curUser;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        if (users.containsKey(id)) {
            return Optional.of(users.get(id));
        }

        return Optional.empty();
    }

}
