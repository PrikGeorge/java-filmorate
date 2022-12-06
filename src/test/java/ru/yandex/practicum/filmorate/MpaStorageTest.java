package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.mpa.MpaStorage;

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
class MpaStorageTest {

    private final MpaStorage mpaStorage;

    @Test
    @DisplayName("Get MPA by id")
    void shouldReturnMpa() {
        Optional<Mpa> userOptional = mpaStorage.findById(1L);
        assertThat(userOptional)
                .isPresent()
                .hasValueSatisfying(user ->
                        assertThat(user).hasFieldOrPropertyWithValue("id", 1L)
                );
    }

    @Test
    @DisplayName("Get empty optional with wrong id")
    void shouldReturnEmptyOptionalIfWrongId() {
        Optional<Mpa> userOptional = mpaStorage.findById(99L);
        assertThat(userOptional)
                .isNotPresent();
    }

    @Test
    @DisplayName("Get mpa size 5")
    void shouldReturnMpaSize_5() {
        List<Mpa> mpa = mpaStorage.getAll();
        assertThat(mpa).hasSize(5);
    }
}
