package ru.yandex.practicum.filmorate.model;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 20.12.2022
 */
public enum SortType {
    YEAR("year"),
    LIKES("likes"),
    UNKNOWN("unknown");

    private final String nameLowerCase;

    public String getNameLowerCase() {
        return nameLowerCase;
    }

    SortType(String nameLowerCase) {
        this.nameLowerCase = nameLowerCase;
    }
}
