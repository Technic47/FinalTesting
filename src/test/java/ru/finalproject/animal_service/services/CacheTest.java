package ru.finalproject.animal_service.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.finalproject.animal_service.models.AnimalToShow;
import ru.finalproject.animal_service.models.animals.Cat;
import ru.finalproject.animal_service.models.animals.abstracts.Actionable;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.finalproject.animal_service.models.animals.abstracts.TestCredentials.*;

@SpringBootTest
class CacheTest {
    private Cache cache;


    @BeforeEach
    void setUp() {
        this.cache = new Cache();
        cache.addAnimalToCache(TEST_CAT.getClass().getSimpleName(), TEST_CAT);
        cache.addAnimalToCache(TEST_CAMEL.getClass().getSimpleName(), TEST_CAMEL);
        cache.addAnimalToCache(TEST_DOG.getClass().getSimpleName(), TEST_DOG);
        cache.addAnimalToCache(TEST_DONKEY.getClass().getSimpleName(), TEST_DONKEY);
        cache.addAnimalToCache(TEST_HUMSTER.getClass().getSimpleName(), TEST_HUMSTER);
        cache.addAnimalToCache(TEST_HORSE.getClass().getSimpleName(), TEST_HORSE);
        cache.addFoodToCache(TEST_FOOD);
        cache.addMovesToCache(TEST_MOVE);
    }

    @Test
    void addAnimalToCache() {
        assertThat(cache.getCatList()).isNotNull();
        assertThat(cache.getCatList()).hasSize(1);
        assertTrue(cache.getCatList().contains(TEST_CAT));
        assertThat(cache.getDogList()).isNotNull();
        assertThat(cache.getDogList()).hasSize(1);
        assertTrue(cache.getDogList().contains(TEST_DOG));
        assertThat(cache.getHumsterList()).isNotNull();
        assertThat(cache.getHumsterList()).hasSize(1);
        assertTrue(cache.getHumsterList().contains(TEST_HUMSTER));
        assertThat(cache.getHorseList()).isNotNull();
        assertThat(cache.getHorseList()).hasSize(1);
        assertTrue(cache.getHorseList().contains(TEST_HORSE));
        assertThat(cache.getDonkeyList()).isNotNull();
        assertThat(cache.getDonkeyList()).hasSize(1);
        assertTrue(cache.getDonkeyList().contains(TEST_DONKEY));
        assertThat(cache.getCamelList()).isNotNull();
        assertThat(cache.getCamelList()).hasSize(1);
        assertTrue(cache.getCamelList().contains(TEST_CAMEL));

        assertThat(cache.getAllAnimalsToShow()).isNotNull();
        assertThat(cache.getAllAnimalsToShow()).hasSize(6);
    }

    @Test
    void delAnimalFromCache() {
        cache.delAnimalFromCache(TEST_CAT.getClass().getSimpleName(), TEST_CAT);
        cache.delAnimalFromCache(TEST_CAMEL.getClass().getSimpleName(), TEST_CAMEL);
        cache.delAnimalFromCache(TEST_DOG.getClass().getSimpleName(), TEST_DOG);
        cache.delAnimalFromCache(TEST_DONKEY.getClass().getSimpleName(), TEST_DONKEY);
        cache.delAnimalFromCache(TEST_HUMSTER.getClass().getSimpleName(), TEST_HUMSTER);
        cache.delAnimalFromCache(TEST_HORSE.getClass().getSimpleName(), TEST_HORSE);

        assertThat(cache.getCatList()).hasSize(0);
        assertFalse(cache.getCatList().contains(TEST_CAT));
        assertThat(cache.getDogList()).hasSize(0);
        assertFalse(cache.getDogList().contains(TEST_DOG));
        assertThat(cache.getHumsterList()).hasSize(0);
        assertFalse(cache.getHumsterList().contains(TEST_HUMSTER));
        assertThat(cache.getHorseList()).hasSize(0);
        assertFalse(cache.getHorseList().contains(TEST_HORSE));
        assertThat(cache.getDonkeyList()).hasSize(0);
        assertFalse(cache.getDonkeyList().contains(TEST_DONKEY));
        assertThat(cache.getCamelList()).hasSize(0);
        assertFalse(cache.getCamelList().contains(TEST_CAMEL));

        assertThat(cache.getAllAnimalsToShow()).isNotNull();
        assertThat(cache.getAllAnimalsToShow()).hasSize(0);
    }

    @Test
    void addFoodToCache() {
        assertThat(cache.getFoodList()).isNotNull();
        assertThat(cache.getFoodList()).hasSize(1);
        assertTrue(cache.getFoodList().contains(TEST_FOOD));

    }

    @Test
    void delFoodFromCache() {
        cache.delFoodFromCache(TEST_FOOD);

        assertThat(cache.getFoodList()).hasSize(0);
        assertFalse(cache.getFoodList().contains(TEST_FOOD));
    }

    @Test
    void addMovesToCache() {
        assertThat(cache.getMovesList()).isNotNull();
        assertThat(cache.getMovesList()).hasSize(1);
        assertTrue(cache.getMovesList().contains(TEST_MOVE));
    }

    @Test
    void delMovesFromCache() {
        cache.delMovesFromCache(TEST_MOVE);

        assertThat(cache.getMovesList()).hasSize(0);
        assertFalse(cache.getMovesList().contains(TEST_MOVE));
    }

    @Test
    void getAllAnimalsToShow() {
        List<AnimalToShow> list = cache.getAllAnimalsToShow();

        assertThat(list).hasSize(6);
        Optional<AnimalToShow> cat = list.stream().filter(item -> item.getType().equals("Cat"))
                .findFirst();

        assertTrue(cat.isPresent());
    }

    @Test
    void convertToShow() {
        cache = new Cache();
        List<Cat> catList = new ArrayList<>();
        catList.add(TEST_CAT);
        catList.add(TEST_CAT);
        catList.add(TEST_CAT);
        cache.setCatList(catList);
        cache.convertToShow();

        assertThat(cache.getAllAnimalsToShow()).hasSize(3);
    }

    @Test
    void updateAnimalToShowList() {
        Set<Long> foodSet = new HashSet<>();
        foodSet.add(TEST_ID);
        TEST_CAT.setFood(foodSet);
        cache.updateAnimalToShowList("Cat", TEST_CAT);
        List<AnimalToShow> list = cache.getAllAnimalsToShow();

        AnimalToShow cat = list.stream().filter(item -> item.getType().equals("Cat"))
                .findFirst().get();

        assertNotNull(cat);
        assertThat(cat.getFoodList()).isNotEmpty();
        assertThat(cat.getFoodList()).hasSize(1);
        assertTrue(cat.getFoodList().contains(TEST_NAME));

    }

    @Test
    void getAnimal() {
        Actionable animal = cache.getAnimal("Cat", TEST_ID);

        assertNotNull(animal);
        assertEquals(TEST_ID, animal.getId());
        assertEquals(TEST_NAME, animal.getName());
        assertTrue(animal instanceof Cat);
    }

    @Test
    void getAnimalToShow() {
        AnimalToShow animal = cache.getAnimalToShow("Cat", TEST_ID);
        assertNotNull(animal);
        assertEquals(TEST_ID, animal.getId());
        assertEquals(TEST_NAME, animal.getName());
        assertEquals("Cat", animal.getType());
    }

    @Test
    void clearAll() {
        cache.clearAll();

        assertTrue(cache.getAllAnimalsToShow().isEmpty());
        assertTrue(cache.getCatList().isEmpty());
        assertTrue(cache.getDogList().isEmpty());
        assertTrue(cache.getHumsterList().isEmpty());
        assertTrue(cache.getHorseList().isEmpty());
        assertTrue(cache.getDonkeyList().isEmpty());
        assertTrue(cache.getCamelList().isEmpty());
        assertTrue(cache.getFoodList().isEmpty());
        assertTrue(cache.getMovesList().isEmpty());
    }
}