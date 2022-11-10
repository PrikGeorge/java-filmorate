package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.user.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 31.10.2022
 */

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl service;

    @Autowired
    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return service.create(user);
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        return service.update(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public boolean addFriend(@PathVariable(value = "id") Long id, @PathVariable(value = "friendId") Long friendId) {
        return service.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public boolean deleteFriend(@PathVariable(value = "id") Long id, @PathVariable(value = "friendId") Long friendId) {
        return service.deleteFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<User> getFriends(@PathVariable(value = "id") Long id) {
        return service.getFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getMutualFriends(@PathVariable(value = "id") Long id, @PathVariable(value = "otherId") Long otherId) {
        return service.getMutualFriends(id, otherId);
    }

}



