package ru.finalproject.animal_service.repositories;

import org.springframework.stereotype.Repository;
import ru.finalproject.animal_service.models.animals.Horse;
import ru.finalproject.animal_service.repositories.abstracts.AbstractRepo;

@Repository
public interface HorseRepository extends AbstractRepo<Horse> {
}
