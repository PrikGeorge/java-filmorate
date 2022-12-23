package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.Storage;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 10.11.2022
 */

public interface UserStorage extends Storage<User> {

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

    List<Film> getRecommendation(Long id);
}
