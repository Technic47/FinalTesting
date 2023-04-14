package ru.finalproject.animal_service.models.animals.abstracts;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Animals implements Actionable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Size(min = 1, max = 100)
    @NotBlank(message = "Поле не должно быть пустым!")
    @Column(name = "name")
    protected String name;

    @ElementCollection(
            fetch = FetchType.EAGER)
    @CollectionTable(name = "animal_moves",
            joinColumns = @JoinColumn(name = "animal_id"))
    protected Set<Long> moves = new HashSet<>();

    @ElementCollection(
            fetch = FetchType.EAGER)
    @CollectionTable(name = "animal_food",
            joinColumns = @JoinColumn(name = "animal_id"))
    protected Set<Long> food = new HashSet<>();

    public Animals(String name) {
        this.name = name;
    }

    public Animals() {
    }

    public Animals(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animals)) return false;
        Animals animals = (Animals) o;
        return Objects.equals(id, animals.id) && Objects.equals(name, animals.name) && Objects.equals(moves, animals.moves) && Objects.equals(food, animals.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, moves, food);
    }

    @Override
    public String toString() {
        return "Animals{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", moves=" + moves +
                ", food=" + food +
                '}';
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

    @Override
    public Set<Long> getMoves() {
        return moves;
    }

    public void setMoves(Set<Long> moves) {
        this.moves = moves;
    }

    @Override
    public Set<Long> getFood() {
        return food;
    }

    public void setFood(Set<Long> food) {
        this.food = food;
    }
}
