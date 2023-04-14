package ru.finalproject.animal_service.models.animals;

import jakarta.persistence.Entity;
import ru.finalproject.animal_service.models.animals.abstracts.WorkAnimal;

@Entity
public class Donkey extends WorkAnimal {
    public Donkey() {
        super();
    }

    public Donkey(String name, Integer workHours) {
        super(name, workHours);
    }

    public Donkey(Long id, String name, Integer workHours) {
        super(id, name, workHours);
    }

    @Override
    public String toString() {
        return "Осёл";
    }
}
