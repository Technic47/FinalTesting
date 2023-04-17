package ru.finalproject.animal_service.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class CounterTest {
    private final Counter counter = new Counter("Cat");

    @Test
    void creationTest() {
        Counter newCounter = new Counter("Dog");

        assertNotEquals(counter, newCounter);
        assertEquals("Cat", counter.getAnimalType());
        assertEquals(1, counter.getCount());
        assertEquals("Cat - 1", counter.toString());

    }

    @Test
    void countPlus() {
        counter.countPlus();
        assertEquals(2, counter.getCount());
    }

    @Test
    void countMinus() {
        counter.countMinus();
        assertEquals(0, counter.getCount());
    }
}