package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 10.11.2022
 */

public interface UserService {

    /**
     * Добавление пользователей в друзья
     *
     * @param id       идентификатор первого пользователя
     * @param friendId идентификатор второго пользователя
     * @return boolean
     */
    boolean addFriend(Long id, Long friendId);

    /**
     * Удаление пользователей из друзей
     *
     * @param id       идентификатор первого пользователя
     * @param friendId идентификатор второго пользователя
     * @return boolean
     */
    boolean deleteFriend(Long id, Long friendId);

    /**
     * Поиск общих друзей
     *
     * @param id       идентификатор первого пользователя
     * @param friendId идентификатор второго пользователя
     * @return List
     */
    List<User> getMutualFriends(Long id, Long friendId);

    /**
     * Получение друзей пользователя
     *
     * @param id идентификатор пользователя
     * @return List
     */
    List<User> getFriends(Long id);

    /**
     * Поиск пользователя по id
     *
     * @param id идентификатор пользователя
     * @return User
     */
    User findById(Long id);

    /**
     * Поиск всех пользователей
     *
     * @return List
     */
    List<User> getAll();

    /**
     * Обновление пользователя
     *
     * @param user
     * @return User
     */
    User update(User user);

    /**
     * Создание пользователя
     *
     * @param user
     * @return User
     */
    User create(User user);

    /**
     * Удаление пользователя
     *
     * @param id
     * @return boolean
     */
    boolean remove(Long id);
}
