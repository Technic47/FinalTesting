package ru.finalproject.animal_service.services.entityServices;

import org.springframework.stereotype.Service;
import ru.finalproject.animal_service.models.animals.Camel;
import ru.finalproject.animal_service.repositories.CamelRepository;
import ru.finalproject.animal_service.services.abstracts.AnimalService;

@Service
public class CamelService extends AnimalService<Camel, CamelRepository> {
    protected CamelService(CamelRepository repository) {
        super(repository);
    }
}
