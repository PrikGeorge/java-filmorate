package ru.yandex.practicum.filmorate.exception;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 31.10.2022
 */

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
