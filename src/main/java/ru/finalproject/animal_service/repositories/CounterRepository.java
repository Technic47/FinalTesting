package ru.finalproject.animal_service.repositories;

import org.springframework.stereotype.Repository;
import ru.finalproject.animal_service.models.Counter;
import ru.finalproject.animal_service.repositories.abstracts.AbstractRepo;

@Repository
public interface CounterRepository extends AbstractRepo<Counter> {
    Counter findByAnimalType(String animalType);
}
