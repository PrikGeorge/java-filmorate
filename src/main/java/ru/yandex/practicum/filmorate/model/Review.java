package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.NonFinal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @NonFinal
    @Setter
    private Long reviewId;

    @NotNull
    private Long userId;

    @NotNull
    private Long filmId;

    @NonFinal
    @Setter
    @NotNull
    private String content;

    @NonFinal
    @Setter
    @NotNull
    private Boolean isPositive;

    @NonFinal
    @Setter
    private Integer useful;
}
