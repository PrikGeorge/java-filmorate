package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 28.12.2022
 */

@Getter
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Event {

    @Setter
    @NotNull
    Long eventId;

    @NotNull
    Long userId;

    @NotNull
    Long entityId;

    @NotNull
    @Size(max = 10)
    EventType eventType;

    @NotNull
    @Size(max = 10)
    Operation operation;

    @NotNull
    Long timestamp;
}
