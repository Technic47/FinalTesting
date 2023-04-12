package ru.finalproject.animal_service.repositories;

import org.springframework.stereotype.Repository;
import ru.finalproject.animal_service.models.Food;
import ru.finalproject.animal_service.repositories.abstracts.AbstractRepo;

@Repository
public interface FoodRepository extends AbstractRepo<Food> {
}
