package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.yandex.practicum.filmorate.controller.user.UserController;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController controller;

    @AfterEach
    void clear() {
        this.controller = null;
    }

    @Test
    @DisplayName("GET getAll")
    void testGetAllEmpty() throws Exception {
        this.mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DisplayName("POST createUser")
    void testCreateUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content("{\"id\":null,\"friends\":[],\"email\":\"mail2@yandex.ru\"," +
                                "\"login\":\"doloreUpdate2\",\"name\":\"est adipisicing2\"" +
                                ",\"birthday\":\"1976-09-20\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{\"id\":7,\"friends\":[],\"email\":\"mail2@yandex.ru\"" +
                                ",\"login\":\"doloreUpdate2\",\"name\":\"est adipisicing2\"" +
                                ",\"birthday\":\"1976-09-20\"}"));
    }

    @Test
    @DisplayName("PUT updateUser")
    void testUpdateUser() throws Exception {
        User user = User.builder()
                .email("email@email.email")
                .login("login")
                .name("name")
                .birthday(LocalDate.of(1914, 7, 28))
                .build();

        controller.create(user);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/users")
                        .content("{\"id\":1,\"friends\":[],\"email\":\"mail3@yandex.ru\"," +
                                "\"login\":\"doloreUpdate\",\"name\":\"est adipisicing\"" +
                                ",\"birthday\":\"1976-09-20\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{\"id\":1,\"friends\":[],\"email\":\"mail3@yandex.ru\"" +
                                ",\"login\":\"doloreUpdate\",\"name\":\"est adipisicing\"" +
                                ",\"birthday\":\"1976-09-20\"}"));
    }

    @Test
    @DisplayName("GET findById")
    void testFindById() throws Exception {
        User user = controller.create(User.builder()
                .email("email4@email.email")
                .login("login")
                .name("name")
                .birthday(LocalDate.of(1914, 7, 28))
                .build());

        String result = String.format("{\"id\":%s,\"email\":\"email4@email.email\",\"login\":\"login\"," +
                "\"name\":\"name\",\"birthday\":\"1914-07-28\",\"friends\":[]}", user.getId());

        this.mockMvc.perform(get("/users/{id}", user.getId()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }

    @Test
    @DisplayName("PUT addFriend")
    void testAddFriend() throws Exception {
        User firstUser = controller.create(User.builder()
                .email("email5@email.email")
                .login("login5")
                .name("name5")
                .birthday(LocalDate.of(1914, 7, 28))
                .build());

        User secondUser = controller.create(User.builder()
                .email("email6@email.email")
                .login("login7")
                .name("name5")
                .birthday(LocalDate.of(1914, 7, 28))
                .build());

        this.mockMvc.perform(put("/users/{id}/friends/{friendId}", firstUser.getId(), secondUser.getId()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }

    @Test
    @DisplayName("DELETE deleteFriend")
    void testDeleteFriend() throws Exception {
        User firstUser = controller.create(User.builder()
                .email("email9@email.email")
                .login("login9")
                .name("name")
                .birthday(LocalDate.of(1914, 7, 28))
                .build());

        User secondUser = controller.create(User.builder()
                .email("email10@email.email")
                .login("login10")
                .name("name")
                .birthday(LocalDate.of(1914, 7, 28))
                .build());

        controller.addFriend(firstUser.getId(), secondUser.getId());

        this.mockMvc.perform(delete("/users/{id}/friends/{friendId}", firstUser.getId(), secondUser.getId()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

}
