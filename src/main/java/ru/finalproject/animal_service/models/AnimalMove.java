package ru.finalproject.animal_service.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AnimalMove {
    @Column(name = "animal_type")
    private String animal_type;
    @Column(name = "move_id")
    private Long move_id;

    public AnimalMove() {
    }

    public AnimalMove(String animal_type, Long move_id) {
        this.animal_type = animal_type;
        this.move_id = move_id;
    }

    public String getAnimal_type() {
        return animal_type;
    }

    public void setAnimal_type(String animal_type) {
        this.animal_type = animal_type;
    }

    public Long getMove_id() {
        return move_id;
    }

    public void setMove_id(Long move_id) {
        this.move_id = move_id;
    }
}
