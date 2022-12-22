package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.NonFinal;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @NonFinal
    @Setter
    @NotBlank
    private Long reviewId;

    @NotBlank
    private Long userId;

    @NotBlank
    private Long filmId;

    @NonFinal
    @Setter
    @NotBlank
    private String content;

    @NonFinal
    @Setter
    @NotBlank
    private Boolean isPositive;

    @NotBlank
    @NonFinal
    @Setter
    private Integer useful;
}
