package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 28.12.2022
 */

@Getter
@Builder
public class Event {

    @Setter
    @NotNull
    private Long eventId;

    @NotNull
    private Long userId;

    @NotNull
    private Long entityId;

    @NotNull
    @Size(max = 10)
    private EventType eventType;

    @NotNull
    @Size(max = 10)
    private Operation operation;

    @NotNull
    private Long timestamp;
}
