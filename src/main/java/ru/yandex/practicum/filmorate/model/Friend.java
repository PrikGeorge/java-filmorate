package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    private Long fromId;
    private Long toId;
}
