package ru.finalproject.animal_service.services.entityServices;

import org.springframework.stereotype.Service;
import ru.finalproject.animal_service.models.animals.Horse;
import ru.finalproject.animal_service.repositories.HorseRepository;
import ru.finalproject.animal_service.services.abstracts.AnimalService;

@Service
public class HorseService extends AnimalService<Horse, HorseRepository> {
    protected HorseService(HorseRepository repository) {
        super(repository);
    }
}
