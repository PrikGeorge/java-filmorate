package ru.yandex.practicum.filmorate.storage.event;

import ru.yandex.practicum.filmorate.model.Event;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 28.12.2022
 */

public interface EventStorage {
    List<Event> getEventsByUserId(Long id);

    Event create(Event event);
}
