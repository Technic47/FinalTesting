package ru.finalproject.animal_service.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.finalproject.animal_service.models.animals.abstracts.TestCredentials.*;

@SpringBootTest
class AnimalToShowTest {
    List<String> list;
    private AnimalToShow item;

    @BeforeEach
    void setUp() {
        this.item = new AnimalToShow(TEST_CAT);
        this.list = new ArrayList<>();
        list.add(TEST_NAME);
        list.add(TEST_NAME);
        list.add(TEST_NAME);
    }

    @Test
    void create() {
        assertEquals(TEST_ID, item.getId());
        assertEquals(TEST_NAME, item.getName());
        assertEquals("Кошка", TEST_CAT.toString());
        assertEquals("Cat", item.getType());
    }

    @Test
    void listsTesting() {
        this.item.setMovesList(list);
        this.item.setFoodList(list);

        assertThat(item.getMovesList()).isNotEmpty();
        assertThat(item.getFoodList()).isNotEmpty();
        assertThat(item.getMovesList()).hasSize(3);
        assertThat(item.getFoodList()).hasSize(3);
    }

    @Test
    void toStringTest(){
        String test = "Кошка, " + TEST_NAME + " Умеет:" +
                item.getMovesList() +
                "; Ест:" + item.getFoodList();
        assertEquals(test, item.toString());
    }
}