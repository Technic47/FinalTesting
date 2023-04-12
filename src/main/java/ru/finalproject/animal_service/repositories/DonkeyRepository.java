package ru.finalproject.animal_service.repositories;

import org.springframework.stereotype.Repository;
import ru.finalproject.animal_service.models.animals.Donkey;
import ru.finalproject.animal_service.repositories.abstracts.AbstractRepo;

@Repository
public interface DonkeyRepository extends AbstractRepo<Donkey> {
}
