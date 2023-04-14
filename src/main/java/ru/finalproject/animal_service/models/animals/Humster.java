package ru.finalproject.animal_service.models.animals;

import jakarta.persistence.Entity;
import ru.finalproject.animal_service.models.animals.abstracts.HomeAnimal;
@Entity
public class Humster extends HomeAnimal {
    public Humster() {
        super(true);
    }

    public Humster(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Хомяк";
    }
}
