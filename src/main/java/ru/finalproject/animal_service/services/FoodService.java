package ru.finalproject.animal_service.services;

import org.springframework.stereotype.Service;
import ru.finalproject.animal_service.models.Food;
import ru.finalproject.animal_service.repositories.FoodRepository;
import ru.finalproject.animal_service.services.abstracts.AbstractService;

@Service
public class FoodService extends AbstractService<Food, FoodRepository> {
    protected FoodService(FoodRepository repository) {
        super(repository);
    }
}
