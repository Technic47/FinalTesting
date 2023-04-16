package ru.finalproject.animal_service.services.entityServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.finalproject.animal_service.models.Counter;
import ru.finalproject.animal_service.repositories.CounterRepository;

import java.io.FileReader;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class CounterServiceTest {
    private static final String PATH_TO_SAVE = "src/main/resources/counter.txt";
    private Counter counter = new Counter();
    private CounterService service;
    @MockBean
    private CounterRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new CounterService(repository);
    }

    @Test
    void countPlus() {
        Counter catCounter = mock(Counter.class);
        doReturn(catCounter)
                .when(repository)
                .findByAnimalType("Cat");
        doReturn(null)
                .when(repository)
                .findByAnimalType("Dog");

        service.countPlus("Cat");
        service.countPlus("Dog");

        try (FileReader reader = new FileReader(PATH_TO_SAVE)) {
            Scanner scanner = new Scanner(reader);
            assertTrue(scanner.hasNextLine());
            assertTrue(scanner.nextLine().contains("null - 0"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        verify(repository, times(1)).findByAnimalType("Cat");
        verify(catCounter, times(1)).countPlus();
        verify(repository, times(1)).save(catCounter);
        verify(repository, times(1)).findByAnimalType("Dog");
        verify(repository, times(1)).save(new Counter("Dog"));
    }

    @Test
    void countMinus() {
        Counter catCounter = mock(Counter.class);
        catCounter.setAnimalType("Cat");
        doReturn(catCounter)
                .when(repository)
                .findByAnimalType("Cat");
        doReturn(null)
                .when(repository)
                .findByAnimalType("Dog");

        service.countMinus("Cat");
        service.countMinus("Dog");

        verify(repository, times(1)).findByAnimalType("Cat");
        verify(catCounter, times(1)).countMinus();
        verify(repository, times(1)).save(catCounter);
        verify(repository, times(1)).findByAnimalType("Dog");
        verify(repository, times(0)).save(new Counter("Dog"));
    }
}