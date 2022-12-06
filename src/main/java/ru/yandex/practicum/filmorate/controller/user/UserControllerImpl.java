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

    /**
     * Создание пользователя
     *
     * @param user
     * @return User
     */
    @Override
    public User create(@Valid @RequestBody User user) {
        return service.create(user);
    }

    /**
     * Обновление пользователя
     *
     * @param user
     * @return User
     */
    @Override
    public User update(@Valid @RequestBody User user) {
        return service.update(user);
    }

    /**
     * Получение списка всех пользователей
     *
     * @return List
     */
    @Override
    public List<User> getAll() {
        return service.getAll();
    }

    /**
     * Получение пользователя по id
     *
     * @param id
     * @return User
     */
    @Override
    public User findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    /**
     * Добавление пользователей в друзья
     *
     * @param id
     * @param friendId
     * @return boolean
     */
    @Override
    public boolean addFriend(@PathVariable(value = "id") Long id, @PathVariable(value = "friendId") Long friendId) {
        return service.addFriend(id, friendId);
    }

    /**
     * Удаление пользователей из друзей
     *
     * @param id
     * @param friendId
     * @return boolean
     */
    @Override
    public boolean deleteFriend(@PathVariable(value = "id") Long id, @PathVariable(value = "friendId") Long friendId) {
        return service.deleteFriend(id, friendId);
    }

    /**
     * Получение друзей пользователя
     *
     * @param id
     * @return List
     */
    @Override
    public List<User> getFriends(@PathVariable(value = "id") Long id) {
        return service.getFriends(id);
    }

    /**
     * Поиск общих друзей
     *
     * @param id
     * @param otherId
     * @return List
     */
    @Override
    public List<User> getMutualFriends(@PathVariable(value = "id") Long id, @PathVariable(value = "otherId") Long otherId) {
        return service.getMutualFriends(id, otherId);
    }

}



