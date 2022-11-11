package ru.yandex.practicum.filmorate.controller.user;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.controller.Operations;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 11.11.2022
 */

@RequestMapping("/users")
public interface UserOperations extends Operations<User> {

    @PutMapping("/{id}/friends/{friendId}")
    boolean addFriend(@PathVariable(value = "id") Long id, @PathVariable(value = "friendId") Long friendId);

    @DeleteMapping("/{id}/friends/{friendId}")
    boolean deleteFriend(@PathVariable(value = "id") Long id, @PathVariable(value = "friendId") Long friendId);

    @GetMapping("/{id}/friends")
    List<User> getFriends(@PathVariable(value = "id") Long id);

    @GetMapping("/{id}/friends/common/{otherId}")
    List<User> getMutualFriends(@PathVariable(value = "id") Long id, @PathVariable(value = "otherId") Long otherId);

}
