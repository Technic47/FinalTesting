package ru.finalproject.animal_service.services.entityServices;

import org.springframework.stereotype.Service;
import ru.finalproject.animal_service.models.animals.Donkey;
import ru.finalproject.animal_service.repositories.DonkeyRepository;
import ru.finalproject.animal_service.services.abstracts.AnimalService;

@Service
public class DonkeyService extends AnimalService<Donkey, DonkeyRepository> {
    protected DonkeyService(DonkeyRepository repository) {
        super(repository);
    }
}
