package ru.finalproject.animal_service.services.abstracts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.finalproject.animal_service.models.animals.Cat;
import ru.finalproject.animal_service.repositories.CatRepository;
import ru.finalproject.animal_service.services.entityServices.CatService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ru.finalproject.animal_service.models.animals.abstracts.TestCredentials.TEST_CAT;
import static ru.finalproject.animal_service.models.animals.abstracts.TestCredentials.TEST_ID;

@SpringBootTest
class AnimalServiceTest {
    private CatService service;
    @MockBean
    private CatRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new CatService(repository);
    }

    @Test
    void saveTest() {
        doReturn(TEST_CAT)
                .when(repository)
                .save(TEST_CAT);
        assertEquals(TEST_CAT, service.save(TEST_CAT));
        verify(repository, times(1)).save(TEST_CAT);
    }

    @Test
    void showTest(){
        doReturn(Optional.of(TEST_CAT))
                .when(repository)
                .findById(TEST_ID);
        assertEquals(TEST_CAT, service.show(TEST_ID));
        verify(repository, times(1)).findById(TEST_ID);
    }

    @Test
    void deleteTest(){
        service.delete(TEST_ID);
        verify(repository, times(1)).deleteById(TEST_ID);
    }

    @Test
    void addToMovesList() {
        Cat cat = TEST_CAT;
        service.addToMovesList(cat, 1L);
        assertEquals(1, cat.getMoves().size());
        verify(repository, times(1)).save(cat);
    }

    @Test
    void delFromMovesList() {
        Cat cat = TEST_CAT;
        service.addToMovesList(cat, 1L);
        service.delFromMovesList(cat, 1L);
        assertEquals(0, cat.getMoves().size());
        verify(repository, times(2)).save(cat);
    }

    @Test
    void addToFoodList() {
        Cat cat = TEST_CAT;
        service.addToFoodList(cat, 1L);
        assertEquals(1, cat.getFood().size());
        verify(repository, times(1)).save(cat);
    }

    @Test
    void delFromFoodList() {
        Cat cat = TEST_CAT;
        service.addToFoodList(cat, 1L);
        service.delFromFoodList(cat, 1L);
        assertEquals(0, cat.getFood().size());
        verify(repository, times(2)).save(cat);
    }
}