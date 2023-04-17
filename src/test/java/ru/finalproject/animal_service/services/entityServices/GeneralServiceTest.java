package ru.finalproject.animal_service.services.entityServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.finalproject.animal_service.models.AnimalToShow;
import ru.finalproject.animal_service.models.Food;
import ru.finalproject.animal_service.models.Moves;
import ru.finalproject.animal_service.models.animals.abstracts.Actionable;
import ru.finalproject.animal_service.services.Cache;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ru.finalproject.animal_service.models.animals.abstracts.TestCredentials.*;

@SpringBootTest
class GeneralServiceTest {

    private GeneralService service;
    @MockBean
    private CatService catService;
    @MockBean
    private DogService dogService;
    @MockBean
    private CamelService camelService;
    @MockBean
    private DonkeyService donkeyService;
    @MockBean
    private HorseService horseService;
    @MockBean
    private HumsterService humsterService;
    @MockBean
    private MovesService movesService;
    @MockBean
    private FoodService foodService;
    @MockBean
    private CounterService counterService;
    @MockBean
    private Cache cache;


    @BeforeEach
    void setUp() {
        this.service = new GeneralService(catService, dogService, camelService, donkeyService, horseService, humsterService, movesService, foodService, counterService, cache);
    }

    @Test
    void verifyPostConstruct() {
//        verify(cache, times(1)).setCatList(anyList());
//        verify(cache, times(1)).setDogList(anyList());
//        verify(cache, times(1)).setHumsterList(anyList());
//        verify(cache, times(1)).setHorseList(anyList());
//        verify(cache, times(1)).setDonkeyList(anyList());
//        verify(cache, times(1)).setCamelList(anyList());
//        verify(cache, times(1)).setMovesList(anyList());
//        verify(cache, times(1)).setFoodList(anyList());
//        verify(cache).convertToShow();
    }

    @Test
    void saveAnimalNew() {
        doReturn(TEST_CAT)
                .when(catService)
                .save(TEST_CAT);

        service.saveAnimal(TEST_CAT, true);

        verify(catService, times(1)).save(TEST_CAT);
        verify(counterService, times(1)).countPlus("Cat");
        verify(cache, times(1)).addAnimalToCache("Cat", TEST_CAT);
    }

    @Test
    void saveAnimalUpdate() {
        doReturn(TEST_CAT)
                .when(catService)
                .save(TEST_CAT);

        service.saveAnimal(TEST_CAT, false);

        verify(catService, times(1)).save(TEST_CAT);
        verify(counterService, times(0)).countPlus("Cat");
        verify(cache, times(0)).addAnimalToCache("Cat", TEST_CAT);
        verify(cache, times(1)).updateAnimalToShowList("Cat", TEST_CAT);
    }

    @Test
    void saveAnimalServiceCheck() {
        service.saveAnimal(TEST_CAT, true);
        service.saveAnimal(TEST_DOG, true);
        service.saveAnimal(TEST_HUMSTER, true);
        service.saveAnimal(TEST_HORSE, true);
        service.saveAnimal(TEST_DONKEY, true);
        service.saveAnimal(TEST_CAMEL, true);

        verify(catService, times(1)).save(TEST_CAT);
        verify(dogService, times(1)).save(TEST_DOG);
        verify(humsterService, times(1)).save(TEST_HUMSTER);
        verify(horseService, times(1)).save(TEST_HORSE);
        verify(donkeyService, times(1)).save(TEST_DONKEY);
        verify(camelService, times(1)).save(TEST_CAMEL);
    }


    @Test
    void delAnimal() {
        service.delAnimal(TEST_CAT);
        service.delAnimal(TEST_DOG);
        service.delAnimal(TEST_HUMSTER);
        service.delAnimal(TEST_HORSE);
        service.delAnimal(TEST_DONKEY);
        service.delAnimal(TEST_CAMEL);

        verify(catService, times(1)).delete(TEST_ID);
        verify(dogService, times(1)).delete(TEST_ID);
        verify(humsterService, times(1)).delete(TEST_ID);
        verify(horseService, times(1)).delete(TEST_ID);
        verify(donkeyService, times(1)).delete(TEST_ID);
        verify(camelService, times(1)).delete(TEST_ID);

        verify(cache, times(1)).delAnimalFromCache("Cat", TEST_CAT);
        verify(cache, times(1)).delAnimalFromCache("Dog", TEST_DOG);
        verify(cache, times(1)).delAnimalFromCache("Humster", TEST_HUMSTER);
        verify(cache, times(1)).delAnimalFromCache("Horse", TEST_HORSE);
        verify(cache, times(1)).delAnimalFromCache("Donkey", TEST_DONKEY);
        verify(cache, times(1)).delAnimalFromCache("Camel", TEST_CAMEL);

        verify(counterService, times(6)).countMinus(anyString());
    }

    @Test
    void getAllAnimalsToShow() {
        List<AnimalToShow> list = new ArrayList<>();
        list.add(new AnimalToShow(TEST_CAT));
        list.add(new AnimalToShow(TEST_DONKEY));
        list.add(new AnimalToShow(TEST_HORSE));
        doReturn(list)
                .when(cache)
                .getAllAnimalsToShow();

        List<AnimalToShow> testList = service.getAllAnimalsToShow();

        verify(cache).getAllAnimalsToShow();
        assertThat(testList).isNotEmpty();
        assertThat(testList).hasSize(3);
    }

    @Test
    void getAnimalToShow() {
        doReturn(new AnimalToShow(TEST_CAT))
                .when(cache)
                .getAnimalToShow("Cat", TEST_ID);

        AnimalToShow animal = service.getAnimalToShow("Cat", TEST_ID);

        verify(cache).getAnimalToShow("Cat", TEST_ID);
        assertEquals("Cat", animal.getType());
        assertEquals(TEST_ID, animal.getId());
    }

    @Test
    void getAnimal() {
        doReturn(TEST_CAT)
                .when(cache)
                .getAnimal("Cat", TEST_ID);

        Actionable animal = service.getAnimal("Cat", TEST_ID);

        verify(cache).getAnimal("Cat", TEST_ID);
        assertEquals(TEST_CAT, animal);
    }

    @Test
    void getMoves() {
        service.getMoves();

        verify(cache).getMovesList();
    }

    @Test
    void getFood() {
        service.getFood();

        verify(cache).getFoodList();
    }

    @Test
    void getCounts() {
        service.getCounts();
        verify(counterService).index();
    }

    @Test
    void addMove() {
        Moves testMove = new Moves(TEST_NAME);
        service.addMove(testMove);

        verify(movesService).save(testMove);
        verify(cache).addMovesToCache(testMove);
    }

    @Test
    void delMove() {
        List<Moves> movesList = new ArrayList<>();
        Moves move = new Moves(TEST_NAME);
        move.setId(TEST_ID);
        movesList.add(move);

        doReturn(movesList)
                .when(cache)
                .getMovesList();

        service.delMove(TEST_NAME);

        verify(movesService).delete(TEST_ID);
        verify(cache).delMovesFromCache(move);
    }

    @Test
    void addFood() {
        Food testMove = new Food(TEST_NAME);
        service.addFood(testMove);

        verify(foodService).save(testMove);
        verify(cache).addFoodToCache(testMove);
    }

    @Test
    void delFood() {
        List<Food> foodList = new ArrayList<>();
        Food move = new Food(TEST_NAME);
        move.setId(TEST_ID);
        foodList.add(move);

        doReturn(foodList)
                .when(cache)
                .getFoodList();

        service.delFood(TEST_NAME);

        verify(foodService).delete(TEST_ID);
        verify(cache).delFoodFromCache(move);
    }

    @Test
    void animalAddMove() {
        service.animalAddMove(TEST_CAT, TEST_ID);
        service.animalAddMove(TEST_DOG, TEST_ID);
        service.animalAddMove(TEST_HUMSTER, TEST_ID);
        service.animalAddMove(TEST_HORSE, TEST_ID);
        service.animalAddMove(TEST_DONKEY, TEST_ID);
        service.animalAddMove(TEST_CAMEL, TEST_ID);

        verify(catService, times(1)).addToMovesList(TEST_CAT, TEST_ID);
        verify(dogService, times(1)).addToMovesList(TEST_DOG, TEST_ID);
        verify(camelService, times(1)).addToMovesList(TEST_CAMEL, TEST_ID);
        verify(horseService, times(1)).addToMovesList(TEST_HORSE, TEST_ID);
        verify(donkeyService, times(1)).addToMovesList(TEST_DONKEY, TEST_ID);
        verify(humsterService, times(1)).addToMovesList(TEST_HUMSTER, TEST_ID);

        verify(cache, times(1)).updateAnimalToShowList("Cat", TEST_CAT);
        verify(cache, times(1)).updateAnimalToShowList("Dog", TEST_DOG);
        verify(cache, times(1)).updateAnimalToShowList("Camel", TEST_CAMEL);
        verify(cache, times(1)).updateAnimalToShowList("Horse", TEST_HORSE);
        verify(cache, times(1)).updateAnimalToShowList("Donkey", TEST_DONKEY);
        verify(cache, times(1)).updateAnimalToShowList("Humster", TEST_HUMSTER);
    }

    @Test
    void animalDelMove() {
        service.animalDelMove(TEST_CAT, TEST_ID);
        service.animalDelMove(TEST_DOG, TEST_ID);
        service.animalDelMove(TEST_HUMSTER, TEST_ID);
        service.animalDelMove(TEST_HORSE, TEST_ID);
        service.animalDelMove(TEST_DONKEY, TEST_ID);
        service.animalDelMove(TEST_CAMEL, TEST_ID);

        verify(catService, times(1)).delFromMovesList(TEST_CAT, TEST_ID);
        verify(dogService, times(1)).delFromMovesList(TEST_DOG, TEST_ID);
        verify(camelService, times(1)).delFromMovesList(TEST_CAMEL, TEST_ID);
        verify(horseService, times(1)).delFromMovesList(TEST_HORSE, TEST_ID);
        verify(donkeyService, times(1)).delFromMovesList(TEST_DONKEY, TEST_ID);
        verify(humsterService, times(1)).delFromMovesList(TEST_HUMSTER, TEST_ID);

        verify(cache, times(1)).updateAnimalToShowList("Cat", TEST_CAT);
        verify(cache, times(1)).updateAnimalToShowList("Dog", TEST_DOG);
        verify(cache, times(1)).updateAnimalToShowList("Camel", TEST_CAMEL);
        verify(cache, times(1)).updateAnimalToShowList("Horse", TEST_HORSE);
        verify(cache, times(1)).updateAnimalToShowList("Donkey", TEST_DONKEY);
        verify(cache, times(1)).updateAnimalToShowList("Humster", TEST_HUMSTER);
    }

    @Test
    void animalAddFood() {
        service.animalAddFood(TEST_CAT, TEST_ID);
        service.animalAddFood(TEST_DOG, TEST_ID);
        service.animalAddFood(TEST_HUMSTER, TEST_ID);
        service.animalAddFood(TEST_HORSE, TEST_ID);
        service.animalAddFood(TEST_DONKEY, TEST_ID);
        service.animalAddFood(TEST_CAMEL, TEST_ID);

        verify(catService, times(1)).addToFoodList(TEST_CAT, TEST_ID);
        verify(dogService, times(1)).addToFoodList(TEST_DOG, TEST_ID);
        verify(camelService, times(1)).addToFoodList(TEST_CAMEL, TEST_ID);
        verify(horseService, times(1)).addToFoodList(TEST_HORSE, TEST_ID);
        verify(donkeyService, times(1)).addToFoodList(TEST_DONKEY, TEST_ID);
        verify(humsterService, times(1)).addToFoodList(TEST_HUMSTER, TEST_ID);

        verify(cache, times(1)).updateAnimalToShowList("Cat", TEST_CAT);
        verify(cache, times(1)).updateAnimalToShowList("Dog", TEST_DOG);
        verify(cache, times(1)).updateAnimalToShowList("Camel", TEST_CAMEL);
        verify(cache, times(1)).updateAnimalToShowList("Horse", TEST_HORSE);
        verify(cache, times(1)).updateAnimalToShowList("Donkey", TEST_DONKEY);
        verify(cache, times(1)).updateAnimalToShowList("Humster", TEST_HUMSTER);
    }

    @Test
    void animalDelFood() {
        service.animalDelFood(TEST_CAT, TEST_ID);
        service.animalDelFood(TEST_DOG, TEST_ID);
        service.animalDelFood(TEST_HUMSTER, TEST_ID);
        service.animalDelFood(TEST_HORSE, TEST_ID);
        service.animalDelFood(TEST_DONKEY, TEST_ID);
        service.animalDelFood(TEST_CAMEL, TEST_ID);

        verify(catService, times(1)).delFromFoodList(TEST_CAT, TEST_ID);
        verify(dogService, times(1)).delFromFoodList(TEST_DOG, TEST_ID);
        verify(camelService, times(1)).delFromFoodList(TEST_CAMEL, TEST_ID);
        verify(horseService, times(1)).delFromFoodList(TEST_HORSE, TEST_ID);
        verify(donkeyService, times(1)).delFromFoodList(TEST_DONKEY, TEST_ID);
        verify(humsterService, times(1)).delFromFoodList(TEST_HUMSTER, TEST_ID);

        verify(cache, times(1)).updateAnimalToShowList("Cat", TEST_CAT);
        verify(cache, times(1)).updateAnimalToShowList("Dog", TEST_DOG);
        verify(cache, times(1)).updateAnimalToShowList("Camel", TEST_CAMEL);
        verify(cache, times(1)).updateAnimalToShowList("Horse", TEST_HORSE);
        verify(cache, times(1)).updateAnimalToShowList("Donkey", TEST_DONKEY);
        verify(cache, times(1)).updateAnimalToShowList("Humster", TEST_HUMSTER);

    }

    @Test
    void setCache() {
    }
}