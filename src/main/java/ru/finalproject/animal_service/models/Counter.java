package ru.finalproject.animal_service.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "animalType")
    private String animalType;
    @Column(name = "count")
    private Integer count = 1;

    public Counter() {
    }

    public Counter(String animalType) {
        this.animalType = animalType;
    }

    public void countPlus() {
        this.count++;
    }

    public void countMinus() {
        this.count--;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public Integer getCount() {
        return count;
    }

//    public void setCount(Integer count) {
//        this.count = count;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Counter)) return false;
        Counter counter = (Counter) o;
        return Objects.equals(id, counter.id) && Objects.equals(animalType, counter.animalType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, animalType, count);
    }
}
