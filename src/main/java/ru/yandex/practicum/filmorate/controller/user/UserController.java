package ru.yandex.practicum.filmorate.controller.user;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.controller.Controllers;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 11.11.2022
 */

@RequestMapping("/users")
public interface UserController extends Controllers<User> {

    /**
     * Добавление пользователей в друзья
     *
     * @param id
     * @param friendId
     * @return boolean
     */
    @PutMapping("/{id}/friends/{friendId}")
    boolean addFriend(@PathVariable(value = "id") Long id, @PathVariable(value = "friendId") Long friendId);

    /**
     * Удаление пользователей из друзей
     *
     * @param id
     * @param friendId
     * @return boolean
     */
    @DeleteMapping("/{id}/friends/{friendId}")
    boolean deleteFriend(@PathVariable(value = "id") Long id, @PathVariable(value = "friendId") Long friendId);

    /**
     * Получение друзей пользователя
     *
     * @param id
     * @return List
     */
    @GetMapping("/{id}/friends")
    List<User> getFriends(@PathVariable(value = "id") Long id);

    /**
     * Поиск общих друзей
     *
     * @param id
     * @param otherId
     * @return List
     */
    @GetMapping("/{id}/friends/common/{otherId}")
    List<User> getMutualFriends(@PathVariable(value = "id") Long id, @PathVariable(value = "otherId") Long otherId);

}
