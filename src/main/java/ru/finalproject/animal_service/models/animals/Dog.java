package ru.finalproject.animal_service.models.animals;

import jakarta.persistence.Entity;
import ru.finalproject.animal_service.models.animals.abstracts.HomeAnimal;

@Entity
public class Dog extends HomeAnimal {
    public Dog() {
        super(false);
    }

    public Dog(Long id, String name) {
        super(id, name, false);
    }

    public Dog(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Собака";
    }
}
