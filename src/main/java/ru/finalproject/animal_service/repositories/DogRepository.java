package ru.finalproject.animal_service.repositories;

import org.springframework.stereotype.Repository;
import ru.finalproject.animal_service.models.animals.Dog;
import ru.finalproject.animal_service.repositories.abstracts.AbstractRepo;

@Repository
public interface DogRepository extends AbstractRepo<Dog> {
}
