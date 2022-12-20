package ru.yandex.practicum.filmorate.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.user.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 31.10.2022
 */

@RestController
public class UserControllerImpl implements UserController {

    private final UserService service;

    @Autowired
    public UserControllerImpl(UserService service) {
        this.service = service;
    }

    @Override
    public User create(@Valid @RequestBody User user) {
        return service.create(user);
    }

    @Override
    public User update(@Valid @RequestBody User user) {
        return service.update(user);
    }

    @Override
    public List<User> getAll() {
        return service.getAll();
    }

    @Override
    public User findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @Override
    public boolean addFriend(@PathVariable(value = "id") Long id, @PathVariable(value = "friendId") Long friendId) {
        return service.addFriend(id, friendId);
    }

    @Override
    public boolean deleteFriend(@PathVariable(value = "id") Long id, @PathVariable(value = "friendId") Long friendId) {
        return service.deleteFriend(id, friendId);
    }

    @Override
    public List<User> getFriends(@PathVariable(value = "id") Long id) {
        return service.getFriends(id);
    }

    @Override
    public List<User> getMutualFriends(@PathVariable(value = "id") Long id, @PathVariable(value = "otherId") Long otherId) {
        return service.getMutualFriends(id, otherId);
    }

    @Override
    public boolean remove(Long id) {
        return service.remove(id);
    }

}



