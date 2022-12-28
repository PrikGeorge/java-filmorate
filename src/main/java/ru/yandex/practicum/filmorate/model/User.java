package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
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
public class User {

    @NonFinal
    @Setter
    Long id;

    @Email
    @NotBlank
    @Size(max = 50)
    String email;

    @NotBlank
    @Pattern(regexp = "\\S*$")
    @Size(max = 50)
    String login;

    @NonFinal
    @Size(max = 50)
    @Setter
    String name;

    @Past
    LocalDate birthday;

    @NonFinal
    @Setter
    Set<Long> friends = new HashSet<>();
}
