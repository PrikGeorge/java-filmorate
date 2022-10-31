package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.utils.GenerateIdentifier;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Long,User> users = new HashMap<>();

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        if (Objects.isNull(user.getName()) || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        user.setId(GenerateIdentifier.INSTANCE.generateId(User.class));
        users.put(user.getId(),user);

        log.debug("Пользователь успешно добавлен.");
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        User curUser = users.get(user.getId());

        if (Objects.nonNull(curUser)) {
            curUser.setName(user.getName());
            curUser.setLogin(user.getLogin());
            curUser.setBirthday(user.getBirthday());
            curUser.setEmail(user.getEmail());

            log.debug("Данные пользователя успешно обновлены.");
            return curUser;
        }

        throw new EntityNotFoundException("Пользователь не найден.");
    }

    @GetMapping
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }
}



