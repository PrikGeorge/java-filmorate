package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.NonFinal;
import ru.yandex.practicum.filmorate.annotation.ReleaseDateValidation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 31.10.2022
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Film {

    @NonFinal
    @Setter
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String description;

    @ReleaseDateValidation(message = "Неверная дата создания фильма", dateStart = "1895.12.28")
    private LocalDate releaseDate;

    @Positive
    private int duration;

    @NonFinal
    @Setter
    private Set<Long> likes = new HashSet<>();

    @NonFinal
    @Setter
    private List<Genre> genres = new ArrayList<>();

    @NotNull
    private Mpa mpa;

}
