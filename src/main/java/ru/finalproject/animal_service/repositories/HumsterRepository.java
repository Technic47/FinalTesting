package ru.finalproject.animal_service.repositories;

import org.springframework.stereotype.Repository;
import ru.finalproject.animal_service.models.animals.Humster;
import ru.finalproject.animal_service.repositories.abstracts.AbstractRepo;

@Repository
public interface HumsterRepository extends AbstractRepo<Humster> {
}
