package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Genre {
    Long id;
    String name;
}
