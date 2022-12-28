package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Film {

    @NonFinal
    @Setter
    Long id;

    @NotBlank
    @Size(max = 50)
    String name;

    @NotBlank
    @Size(max = 200)
    String description;

    @ReleaseDateValidation(message = "Неверная дата создания фильма", dateStart = "1895.12.28")
    LocalDate releaseDate;

    @Positive
    int duration;

    @NonFinal
    @Setter
    Set<Long> likes = new HashSet<>();

    @NonFinal
    @Setter
    List<Genre> genres = new ArrayList<>();

    @NotNull
    Mpa mpa;

    @NonFinal
    @Setter
    List<Director> directors = new ArrayList<>();

}
