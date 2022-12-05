package ru.yandex.practicum.filmorate.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 31.10.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private String message;
    private String debugMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public ApiError(String message, String debugMessage) {
        this.message = message;
        this.debugMessage = debugMessage;
    }
}
