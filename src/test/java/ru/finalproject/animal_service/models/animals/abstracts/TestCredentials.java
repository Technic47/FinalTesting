package ru.finalproject.animal_service.models.animals.abstracts;

import ru.finalproject.animal_service.models.Counter;
import ru.finalproject.animal_service.models.Food;
import ru.finalproject.animal_service.models.Moves;
import ru.finalproject.animal_service.models.animals.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestCredentials {
    public static final Long TEST_ID = 666L;
    public static final String TEST_NAME = "testName";
    public static final Cat TEST_CAT = new Cat(TEST_ID, TEST_NAME);
    public static final Dog TEST_DOG = new Dog(TEST_ID, TEST_NAME);
    public static final Humster TEST_HUMSTER = new Humster(TEST_ID, TEST_NAME);
    public static final Horse TEST_HORSE = new Horse(TEST_ID, TEST_NAME, 4);
    public static final Donkey TEST_DONKEY = new Donkey(TEST_ID, TEST_NAME, 6);
    public static final Camel TEST_CAMEL = new Camel(TEST_ID, TEST_NAME, 10);
    public static final Moves TEST_MOVE = new Moves(TEST_ID, TEST_NAME);
    public static final Food TEST_FOOD = new Food(TEST_ID, TEST_NAME);
    public static final Set<Long> TEST_SET = new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L));
    public static final Counter TEST_COUNTER = new Counter("Cat");
}
