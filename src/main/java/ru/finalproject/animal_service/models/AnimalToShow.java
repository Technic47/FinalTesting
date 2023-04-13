package ru.finalproject.animal_service.models;

import ru.finalproject.animal_service.models.animals.abstracts.Actionable;

import java.util.List;

public class AnimalToShow {
    private final String animalName;
    private final String type;
    private Long id;
    private String name;
    private List<String> foodList;
    private List<String> movesList;

    public AnimalToShow(Actionable animal) {
        this.id = animal.getId();
        this.name = animal.getName();
        this.animalName = animal.toString();
        this.type = animal.getClass().getSimpleName();
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

    public String getAnimalName() {
        return animalName;
    }

    public String getType() {
        return type;
    }

    public List<String> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<String> foodList) {
        this.foodList = foodList;
    }

    public List<String> getMovesList() {
        return movesList;
    }

    public void setMovesList(List<String> movesList) {
        this.movesList = movesList;
    }

    @Override
    public String toString() {
        return this.animalName + ", " + this.name +
                " Умеет:" + getMovesList() +
                "; Ест:" + getFoodList();
    }
}
