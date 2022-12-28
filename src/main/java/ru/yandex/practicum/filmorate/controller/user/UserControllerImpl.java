package ru.yandex.practicum.filmorate.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Event;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.user.UserService;

import java.util.List;
import java.util.Objects;

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
    public User create(User user) {
        return service.create(user);
    }

    @Override
    public User update(User user) {
        return service.update(user);
    }

    @Override
    public List<User> getAll() {
        return service.getAll();
    }

    @Override
    public User findById(Long id) {
        return service.findById(id);
    }

    @Override
    public boolean addFriend(Long id, Long friendId) {
        return Objects.nonNull(service.addFriend(id, friendId));
    }

    @Override
    public boolean deleteFriend(Long id, Long friendId) {
        return Objects.nonNull(service.deleteFriend(id, friendId));
    }

    @Override
    public List<User> getFriends(Long id) {
        return service.getFriends(id);
    }

    @Override
    public List<User> getMutualFriends(Long id, Long otherId) {
        return service.getMutualFriends(id, otherId);
    }

    @Override
    public List<Film> getRecommendations(Long id) {
        return service.getRecommendations(id);
    }

    @Override
    public boolean remove(Long id) {
        return service.remove(id);
    }

    @Override
    public List<Event> getFeed(Long id) {
        return service.getFeed(id);
    }

}



