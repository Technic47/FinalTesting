package ru.finalproject.animal_service.models.animals;


import jakarta.persistence.Entity;
import ru.finalproject.animal_service.models.animals.abstracts.WorkAnimal;
@Entity
public class Horse extends WorkAnimal {
    public Horse() {
        super();
    }

    public Horse(String name, Integer workHours) {
        super(name, workHours);
    }

    @Override
    public String toString() {
        return "Лошадь";
    }
}
