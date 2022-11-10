package ru.yandex.practicum.filmorate.service.user;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.utils.GenerateIdentifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 10.11.2022
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final InMemoryUserStorage storage;

    @Autowired
    public UserServiceImpl(InMemoryUserStorage storage) {
        this.storage = storage;
    }

    @Override
    public boolean addFriend(Long id, Long friendId) {
        User sourceUser = validateUserId(id);
        User targetUser = validateUserId(friendId);

        sourceUser.getFriends().add(targetUser.getId());
        targetUser.getFriends().add(sourceUser.getId());

        return true;
    }

    @Override
    public boolean deleteFriend(Long id, Long friendId) {
        User sourceUser = validateUserId(id);
        User targetUser = validateUserId(friendId);

        sourceUser.getFriends().remove(targetUser.getId());
        targetUser.getFriends().remove(sourceUser.getId());

        return true;
    }

    @Override
    public List<User> getMutualFriends(Long id, Long friendId) {
        User sourceUser = validateUserId(id);
        User targetUser = validateUserId(friendId);

        if (sourceUser.getFriends().size() == 0) {
            return new ArrayList<>();
        }

        List<Long> userIds = sourceUser.getFriends()
                .stream()
                .filter(identity -> targetUser.getFriends().contains(identity))
                .collect(Collectors.toList());

        return storage.getAll().stream()
                .filter(user -> userIds.contains(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getFriends(Long id) {
        Set<Long> userIds = validateUserId(id).getFriends();

        return storage.getAll().stream()
                .filter(user -> userIds.contains(user.getId()))
                .collect(Collectors.toList());
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
        return storage.update(user);
    }

    @Override
    public User create(@NonNull User user) {
        if (Objects.nonNull(user.getId())) {
            validateUserId(user.getId());
        }

        if (Objects.isNull(user.getName()) || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        user.setId(GenerateIdentifier.INSTANCE.generateId(User.class));
        return storage.create(user);
    }


    private User validateUserId(Long id) {
        return storage.findById(id).orElseThrow(() -> {
            log.error("Ошибка при валидации пользователя.");
            throw new EntityNotFoundException("Пользователь с id=" + id + " не найден.");
        });
    }
}
