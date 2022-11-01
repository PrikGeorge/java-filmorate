package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.NonFinal;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class User {

    @NonFinal
    @Setter
    private long id;

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Pattern(regexp = "\\S*$")
    @Size(max = 50)
    private String login;

    @NonFinal
    @Size(max = 50)
    @Setter
    private String name;

    @Past
    LocalDate birthday;

}
