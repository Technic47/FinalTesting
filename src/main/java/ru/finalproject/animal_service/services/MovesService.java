package ru.finalproject.animal_service.services;

import org.springframework.stereotype.Service;
import ru.finalproject.animal_service.models.Moves;
import ru.finalproject.animal_service.repositories.MovesRepository;
import ru.finalproject.animal_service.services.abstracts.AbstractService;

@Service
public class MovesService extends AbstractService<Moves, MovesRepository> {
    protected MovesService(MovesRepository repository) {
        super(repository);
    }
}
