package ru.yandex.practicum.filmorate.utils;

import ru.yandex.practicum.filmorate.model.SortType;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 20.12.2022
 */
public class EnumHelper {

    public static SortType getSortTypeByName(String name) {
        switch (name.toLowerCase()) {
            case "year":
                return SortType.YEAR;
            case "likes":
                return SortType.LIKES;
            default:
                return SortType.UNKNOWN;
        }
    }
}
