package ru.finalproject.animal_service.services;

import org.springframework.stereotype.Service;
import ru.finalproject.animal_service.models.animals.Dog;
import ru.finalproject.animal_service.repositories.DogRepository;
import ru.finalproject.animal_service.services.abstracts.AnimalService;

@Service
public class DogService extends AnimalService<Dog, DogRepository> {
    protected DogService(DogRepository repository) {
        super(repository);
    }
}
