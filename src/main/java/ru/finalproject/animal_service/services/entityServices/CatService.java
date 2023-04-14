package ru.finalproject.animal_service.services.entityServices;

import org.springframework.stereotype.Service;
import ru.finalproject.animal_service.models.animals.Cat;
import ru.finalproject.animal_service.repositories.CatRepository;
import ru.finalproject.animal_service.services.abstracts.AnimalService;

@Service
public class CatService extends AnimalService<Cat, CatRepository> {
    public CatService(CatRepository repository) {
        super(repository);
    }
}
