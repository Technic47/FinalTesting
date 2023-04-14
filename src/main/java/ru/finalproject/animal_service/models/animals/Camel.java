package ru.finalproject.animal_service.models.animals;


import jakarta.persistence.Entity;
import ru.finalproject.animal_service.models.animals.abstracts.WorkAnimal;

@Entity
public class Camel extends WorkAnimal {
    public Camel() {
        super();
    }

    public Camel(String name, Integer workHours) {
        super(name, workHours);
    }

    @Override
    public String toString() {
        return "Верблюд";
    }
}
