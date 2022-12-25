package ru.yandex.practicum.filmorate.utils;

import ru.yandex.practicum.filmorate.model.SearchType;
import ru.yandex.practicum.filmorate.model.SortType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static List<SearchType> getSearchTypesFromString(String searchBy) {
        return Stream.of(searchBy.toLowerCase().split(",")).map(word -> {
            switch (word) {
                case "title":
                    return SearchType.TITLE;
                case "director":
                    return SearchType.DIRECTOR;
                default:
                    return SearchType.UNKNOWN;
            }
        }).collect(Collectors.toList());
    }
}
