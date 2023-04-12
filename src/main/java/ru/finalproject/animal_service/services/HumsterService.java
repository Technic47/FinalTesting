package ru.finalproject.animal_service.services;

import org.springframework.stereotype.Service;
import ru.finalproject.animal_service.models.animals.Humster;
import ru.finalproject.animal_service.repositories.HumsterRepository;
import ru.finalproject.animal_service.services.abstracts.AnimalService;

@Service
public class HumsterService extends AnimalService<Humster, HumsterRepository> {
    protected HumsterService(HumsterRepository repository) {
        super(repository);
    }
}
