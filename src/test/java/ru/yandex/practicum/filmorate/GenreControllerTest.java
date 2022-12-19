package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class GenreControllerTest {

    private final GenreStorage genreStorage;

    @Test
    @DisplayName("Get genre by id")
    void shouldReturnGenre() {
        Optional<Genre> genreOptional = genreStorage.findById(1L);
        assertThat(genreOptional)
                .isPresent()
                .hasValueSatisfying(user ->
                        assertThat(user).hasFieldOrPropertyWithValue("id", 1L)
                );
    }

    @Test
    @DisplayName("Get genre size 6")
    void shouldReturnArrayListGenreSize_6() {
        List<Genre> genre = genreStorage.getAll();
        assertThat(genre).hasSize(6);
    }

}
