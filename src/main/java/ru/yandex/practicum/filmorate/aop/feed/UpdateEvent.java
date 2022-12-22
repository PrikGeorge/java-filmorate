package ru.yandex.practicum.filmorate.aop.feed;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 22.12.2022
 */

@Retention(RUNTIME)
@Target(METHOD)
public @interface UpdateEvent {
}
