package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationTests {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldTrueCreateUser() {
        User user = User.builder()
                .login("dolore")
                .name("Nick Name")
                .email("mail@mail.ru")
                .birthday(LocalDate.of(1946,8,20))
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFalseCreateUserFailLogin() {
        User user = User.builder()
                .login("")
                .name("Nick Name")
                .email("mail@mail.ru")
                .birthday(LocalDate.of(1946,8,20))
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFalseCreateUserFailEmail() {
        User user = User.builder()
                .login("dolore ullamco")
                .name("")
                .email("mail.ru")
                .birthday(LocalDate.of(1980,8,20))
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFalseCreateUserFailBirthday() {
        User user = User.builder()
                .login("dolore")
                .name("")
                .email("test@mail.ru")
                .birthday(LocalDate.of(2446,8,20))
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldTrueCreateFilm() {
        Film film = Film.builder()
                .name("nisi eiusmod")
                .description("adipisicing")
                .releaseDate(LocalDate.of(1967,3,25))
                .duration(100)
                .mpa(Mpa.builder().id(1L).name("G").build())
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFalseCreateFilmFailName() {
        Film film = Film.builder()
                .name("")
                .description("Description")
                .releaseDate(LocalDate.of(1900,3,25))
                .duration(200)
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFalseCreateFilmFailDescription() {
        Film film = Film.builder()
                .name("Film name")
                .description("???????????? ???????????? ( ??????????-???????????? ??????????????), ?????????????????? ?? ?????????? ??????????????." +
                        " ?????????? ?????? ?????????? ?????????????????? ?????????????????? ???????????? ??????????????, ?????????????? ???????????????? ???? ????????????," +
                        " ?? ???????????? 20 ??????????????????. ?? ????????????, ?????????????? ???? ?????????? ?????????????? ??????????????????????, ???????? ???????????????????? ??????????????????.")
                .releaseDate(LocalDate.of(1900,3,25))
                .duration(200)
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFalseCreateUserFilmReleaseDate() {
        Film film = Film.builder()
                .name("Name")
                .description("Description")
                .releaseDate(LocalDate.of(1890,3,25))
                .duration(200)
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }


    @Test
    void shouldFalseCreateUserFilmDuration() {
        Film film = Film.builder()
                .name("Name")
                .description("Descrition")
                .releaseDate(LocalDate.of(1980,3,25))
                .duration(-200)
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }
}
