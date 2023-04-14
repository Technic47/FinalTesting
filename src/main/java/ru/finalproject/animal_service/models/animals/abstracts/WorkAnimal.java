package ru.finalproject.animal_service.models.animals.abstracts;

import jakarta.persistence.Column;

public class WorkAnimal extends Animals {
    @Column(name = "workHours")
    private Integer workHours = 1;

    public WorkAnimal() {
    }

    public WorkAnimal(String name, Integer workHours) {
        super(name);
        this.workHours = workHours;
    }

    public WorkAnimal(Integer workHours) {
        this.workHours = workHours;
    }

    public Integer getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }
}
