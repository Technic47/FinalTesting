package ru.finalproject.animal_service.models;

import jakarta.persistence.*;

@Entity
public class Moves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "name")
    protected String name;

    public Moves() {
    }

    public Moves(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
