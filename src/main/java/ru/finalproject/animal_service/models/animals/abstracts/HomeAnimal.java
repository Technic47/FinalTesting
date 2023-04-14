package ru.finalproject.animal_service.models.animals.abstracts;

import jakarta.persistence.Column;

public class HomeAnimal extends Animals {
    @Column(name = "lazy")
    private boolean lazy = false;

    public HomeAnimal() {
    }

    public HomeAnimal(String name) {
        super(name);
    }

    public HomeAnimal(boolean lazy) {
        this.lazy = lazy;
    }

    public boolean isLazy() {
        return lazy;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }
}
