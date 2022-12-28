package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Review {
    @NonFinal
    @Setter
    Long reviewId;

    @NotNull
    Long userId;

    @NotNull
    Long filmId;

    @NonFinal
    @Setter
    @NotNull
    String content;

    @NonFinal
    @Setter
    @NotNull
    Boolean isPositive;

    @NonFinal
    @Setter
    Integer useful;
}
