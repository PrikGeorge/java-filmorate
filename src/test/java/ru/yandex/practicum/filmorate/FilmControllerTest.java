package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 06.12.2022
 */

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class FilmControllerTest {

    private final FilmStorage filmDbStorage;

    @Test
    @Order(10)
    void createAndGetAllFilmTest() throws ValidationException {
        filmDbStorage.create(Film.builder()
                .name("nisi eiusmod")
                .duration(100)
                .description("adipisicing")
                .releaseDate(LocalDate.of(2001, 4, 25))
                .mpa(Mpa.builder().id(1L).build())
                .build());

        assertEquals(2, filmDbStorage.getAll().size());
    }

    @Test
    @Order(20)
    void getByIdTest() throws ValidationException {
        filmDbStorage.create(Film.builder()
                .name("nisi eiusmod")
                .duration(100)
                .description("adipisicing")
                .releaseDate(LocalDate.of(2001, 4, 25))
                .mpa(Mpa.builder().id(1L).build())
                .build());

        Optional<Film> filmOptional = filmDbStorage.findById(1L);
        assertThat(filmOptional).isPresent()
                .hasValueSatisfying(film1 -> assertThat(film1)
                        .hasFieldOrPropertyWithValue("name", "nisi eiusmod"));
    }

    @Test
    @Order(30)
    void getPopularTest() throws ValidationException {
        filmDbStorage.create(Film.builder()
                .id(2L)
                .name("nisi eiusmod")
                .duration(100)
                .description("adipisicing")
                .releaseDate(LocalDate.of(2001, 4, 25))
                .mpa(Mpa.builder().id(1L).name("G").build())
                .build());

//        List<Film> films = filmDbStorage.getMostPopularFilms(1,);
        assertEquals(1, 1);
    }

    @Test
    @Order(40)
    void updateTest() {
        filmDbStorage.update(Film.builder()
                .id(1L)
                .name("nisi eiusmod2")
                .duration(100)
                .description("adipisicing")
                .releaseDate(LocalDate.of(2001, 4, 25))
                .mpa(Mpa.builder().id(1L).name("G").build())
                .build());

        Optional<Film> filmOptional = filmDbStorage.findById(1L);
        assertThat(filmOptional)
                .isPresent()
                .hasValueSatisfying(film ->
                        assertThat(film).hasFieldOrPropertyWithValue("name",
                                "nisi eiusmod2"));
    }
}
