package ru.finalproject.animal_service.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.finalproject.animal_service.services.entityServices.GeneralService;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.finalproject.animal_service.models.animals.abstracts.TestCredentials.TEST_NAME;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GeneralService service;

    @BeforeEach
    void setUp() {
        this.service.loadValues();
    }

    @AfterEach
    void clear() {
        this.service.clearCache();
    }

    @Test
    void hallo() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(view().name("/index"))
                .andExpect(content().string(containsString("Введите своё имя:")));
    }

    @Test
    void setName() throws Exception {
        this.mockMvc.perform(post("/start")
                        .param("name", TEST_NAME))
                .andDo(print())
                .andExpect(view().name("/animals"))
                .andExpect(model().attribute("username", TEST_NAME))
                .andExpect(model().attribute("allAnimals", hasSize(6)))
                .andExpect(model().attribute("moves", hasSize(3)))
                .andExpect(model().attribute("food", hasSize(3)))
                .andExpect(model().attribute("counts", hasSize(6)))
                .andExpect(content().string(containsString(TEST_NAME)))
                .andExpect(content().string(containsString("Cat1")))
                .andExpect(content().string(containsString("Dog1")))
                .andExpect(content().string(containsString("Humster1")))
                .andExpect(content().string(containsString("Horse1")))
                .andExpect(content().string(containsString("Donkey1")))
                .andExpect(content().string(containsString("Camel1")));
    }

    @Test
    void newAnimal() throws Exception {
        this.mockMvc.perform(post("/start")
                .param("name", TEST_NAME));
        this.mockMvc.perform(get("/new"))
                .andDo(print())
                .andExpect(view().name("/new"))
                .andExpect(model().attribute("username", TEST_NAME))
                .andExpect(model().attribute("allAnimals", hasSize(6)))
                .andExpect(model().attribute("counts", hasSize(6)))
                .andExpect(content().string(containsString(TEST_NAME)))
                .andExpect(content().string(containsString("Cat1")));
    }

    @Test
    void create() throws Exception {
        this.mockMvc.perform(post("/new")
                        .param("name", TEST_NAME)
                        .param("time", "10")
                        .param("type", "Cat"))
                .andDo(print())
                .andExpect(view().name("/animals"))
                .andExpect(model().attribute("allAnimals", hasSize(7)))
                .andExpect(content().string(containsString("Cat - 2")))
                .andExpect(content().string(containsString(TEST_NAME)));
    }

    @Test
    void newFoodMoves() throws Exception {
        this.mockMvc.perform(get("/newFoodMoves"))
                .andDo(print())
                .andExpect(view().name("/newFoodMoves"))
                .andExpect(model().attribute("moves", hasSize(3)))
                .andExpect(model().attribute("food", hasSize(3)))
                .andExpect(model().attribute("counts", hasSize(6)))
                .andExpect(content().string(containsString("Move1")))
                .andExpect(content().string(containsString("Food1")));
    }

    @Test
    void addFoodMoves() throws Exception {
        this.mockMvc.perform(post("/newFoodMoves")
                .param("foodName", "foodName")
                .param("moveName", "moveName"));
        this.mockMvc.perform(get("/newFoodMoves"))
                .andDo(print())
                .andExpect(model().attribute("moves", hasSize(4)))
                .andExpect(model().attribute("food", hasSize(4)))
                .andExpect(content().string(containsString("foodName")))
                .andExpect(content().string(containsString("moveName")));
    }

    @Test
    void delFoodMoves() throws Exception {
        this.mockMvc.perform(post("/delFoodMoves")
                .param("type", "food")
                .param("name", "Food1"));
        this.mockMvc.perform(post("/delFoodMoves")
                .param("type", "moves")
                .param("name", "Move1"));

        this.mockMvc.perform(get("/newFoodMoves"))
                .andDo(print())
                .andExpect(model().attribute("moves", hasSize(2)))
                .andExpect(model().attribute("food", hasSize(2)))
                .andExpect(content().string(not("Move1")))
                .andExpect(content().string(not("Food1")));
    }

    @Test
    void configure() throws Exception {
        this.mockMvc.perform(get("/edit")
                        .param("type", "Cat")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(content().string(containsString("Cat1")))
                .andExpect(model().attribute("type", 0))
                .andExpect(model().attribute("moves", hasSize(3)))
                .andExpect(model().attribute("food", hasSize(3)));
        this.mockMvc.perform(get("/edit")
                        .param("type", "Horse")
                        .param("id", "4"))
                .andExpect(content().string(containsString("Horse1")))
                .andExpect(content().string(containsString("Часы работы:")))
                .andExpect(model().attribute("type", 1));
    }

    @Test
    void edit() throws Exception {
        this.mockMvc.perform(post("/edit")
                        .param("type", "Cat")
                        .param("id", "1")
                        .param("name", "qwerty")
                        .param("moveId", "1")
                        .param("action", "addMove"))
                .andDo(print())
                .andExpect(view().name("/edit"));
        this.mockMvc.perform(post("/edit")
                .param("type", "Cat")
                .param("id", "1")
                .param("foodId", "1")
                .param("action", "addFood"));
        this.mockMvc.perform(get("/edit")
                        .param("type", "Cat")
                        .param("id", "1"))
                .andExpect(content().string(containsString("qwerty")))
                .andExpect(content().string(containsString("Move1")))
                .andExpect(content().string(containsString("Food1")));

        this.mockMvc.perform(post("/edit")
                .param("type", "Cat")
                .param("id", "1")
                .param("foodId", "1")
                .param("action", "delMove"));
        this.mockMvc.perform(post("/edit")
                .param("type", "Cat")
                .param("id", "1")
                .param("foodId", "1")
                .param("action", "delFood"));
        this.mockMvc.perform(get("/edit")
                        .param("type", "Cat")
                        .param("id", "1"))
                .andExpect(content().string(not("Move1")))
                .andExpect(content().string(not("Food1")));

        this.mockMvc.perform(post("/edit")
                .param("type", "Cat")
                .param("id", "1")
                .param("action", "finish"))
                .andExpect(view().name("/animals"));
    }

    @Test
    void delete() throws Exception {
        this.mockMvc.perform(post("/edit")
                        .param("type", "Cat")
                        .param("id", "1")
                        .param("action", "delete"))
                .andExpect(content().string(not("qwerty")))
                .andExpect(content().string(not("Cat1")))
                .andExpect(view().name("/animals"))
                .andExpect(content().string(containsString("Cat - 0")));
    }
}