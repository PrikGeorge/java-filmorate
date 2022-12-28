package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 20.12.2022
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Director {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;
}
