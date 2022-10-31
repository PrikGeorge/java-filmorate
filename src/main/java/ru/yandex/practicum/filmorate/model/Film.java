package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.NonFinal;
import ru.yandex.practicum.filmorate.annotation.ReleaseDateValidation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
public class Film {

    @NonFinal
    @Setter
    private long id;

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

}
