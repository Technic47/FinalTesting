package ru.finalproject.animal_service.models.animals.abstracts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.finalproject.animal_service.models.animals.Cat;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.finalproject.animal_service.models.animals.abstracts.TestCredentials.*;

@SpringBootTest
class AnimalsTest {

    @Test
    void creationTests() {
        List<Actionable> list = new ArrayList<>();
        list.add(TEST_CAT);
        list.add(TEST_DOG);
        list.add(TEST_HUMSTER);
        list.add(TEST_HORSE);
        list.add(TEST_CAMEL);
        list.add(TEST_DONKEY);
        list.forEach(item -> {
            assertEquals(item.getId(), TEST_ID);
            assertEquals(item.getName(), TEST_NAME);
            assertNotNull(item.getMoves());
            assertNotNull(item.getFood());
        });
    }

    @Test
    void equalsTest() {
        Cat newCat = new Cat(TEST_NAME);
        newCat.setId(TEST_ID);
        assertEquals(newCat, TEST_CAT);
        newCat.setId(100L);
        assertNotEquals(newCat, TEST_CAT);
        newCat.setId(TEST_ID);
        newCat.setName("blabla");
        assertNotEquals(newCat, TEST_CAT);
    }

    @Test
    void setsTest() {
        TEST_CAT.setMoves(TEST_SET);
        TEST_CAT.setFood(TEST_SET);
        assertEquals(5, TEST_CAT.getMoves().size());
        assertEquals(5, TEST_CAT.getFood().size());
    }

    @Test
    void toStringTest(){
        assertEquals("Кошка", TEST_CAT.toString());
        assertEquals("Собака", TEST_DOG.toString());
        assertEquals("Хомяк", TEST_HUMSTER.toString());
        assertEquals("Лошадь", TEST_HORSE.toString());
        assertEquals("Осёл", TEST_DONKEY.toString());
        assertEquals("Верблюд", TEST_CAMEL.toString());

    }
}