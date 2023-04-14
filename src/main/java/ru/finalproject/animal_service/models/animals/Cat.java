package ru.finalproject.animal_service.models.animals;

import jakarta.persistence.Entity;
import ru.finalproject.animal_service.models.animals.abstracts.HomeAnimal;

@Entity
public class Cat extends HomeAnimal {
    public Cat() {
        super(true);
    }

    public Cat(Long id, String name) {
        super(id, name, true);
    }

    public Cat(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Кошка";
    }
}
