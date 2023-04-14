package ru.finalproject.animal_service.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.finalproject.animal_service.models.AnimalToShow;
import ru.finalproject.animal_service.models.Food;
import ru.finalproject.animal_service.models.Moves;
import ru.finalproject.animal_service.models.animals.*;
import ru.finalproject.animal_service.models.animals.abstracts.Actionable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
public class Cache {
    private List<Cat> catList = new ArrayList<>();
    private List<Dog> dogList = new ArrayList<>();
    private List<Humster> humsterList = new ArrayList<>();
    private List<Horse> horseList = new ArrayList<>();
    private List<Donkey> donkeyList = new ArrayList<>();
    private List<Camel> camelList = new ArrayList<>();
    private List<Food> foodList = new ArrayList<>();
    private List<Moves> movesList = new ArrayList<>();
    private List<AnimalToShow> animalToShowList = new ArrayList<>();


    public Cache() {
    }

    public void addAnimalToCache(String type, Actionable newAnimal) {
        switch (type) {
            case "Cat" -> this.catList.add((Cat) newAnimal);
            case "Dog" -> this.dogList.add((Dog) newAnimal);
            case "Camel" -> this.camelList.add((Camel) newAnimal);
            case "Horse" -> this.horseList.add((Horse) newAnimal);
            case "Donkey" -> this.donkeyList.add((Donkey) newAnimal);
            case "Humster" -> this.humsterList.add((Humster) newAnimal);
        }
        this.updateAnimalToShowList(type, newAnimal);
    }

    public void delAnimalFromCache(String type, Actionable animal) {
        Long id = animal.getId();
        switch (type) {
            case "Cat" -> this.catList = catList.stream().filter(item -> !Objects.equals(item.getId(), id)).toList();
            case "Dog" -> this.dogList = dogList.stream().filter(item -> !Objects.equals(item.getId(), id)).toList();
            case "Camel" ->
                    this.camelList = camelList.stream().filter(item -> !Objects.equals(item.getId(), id)).toList();
            case "Horse" ->
                    this.horseList = horseList.stream().filter(item -> !Objects.equals(item.getId(), id)).toList();
            case "Donkey" ->
                    this.donkeyList = donkeyList.stream().filter(item -> !Objects.equals(item.getId(), id)).toList();
            case "Humster" ->
                    this.humsterList = humsterList.stream().filter(item -> !Objects.equals(item.getId(), id)).toList();
        }
        this.updateAnimalToShowList(type, animal);
    }

    public void addFoodToCache(Food item) {
        this.foodList.add(item);
    }

    public void delFoodFromCache(Food item) {
        this.foodList.remove(item);
    }

    public void addMovesToCache(Moves item) {
        this.movesList.add(item);
    }

    public void delMovesFromCache(Moves item) {
        this.movesList.remove(item);
    }

    public List<AnimalToShow> getAllAnimalsToShow() {
        return this.animalToShowList;
    }

    public void convertToShow() {
        this.getAllAnimals().forEach(animal -> this.animalToShowList.add(this.conversion(animal)));
    }

    private List<Actionable> getAllAnimals() {
        List<Actionable> newList = new ArrayList<>();
        newList.addAll(catList);
        newList.addAll(dogList);
        newList.addAll(humsterList);
        newList.addAll(horseList);
        newList.addAll(donkeyList);
        newList.addAll(camelList);
        return newList;
    }

    private AnimalToShow conversion(Actionable animal) {
        List<Food> animalFoodList = foodList.stream()
                .filter(item -> animal.getFood().contains(item.getId()))
                .toList();
        List<String> newFoodList = new ArrayList<>();
        animalFoodList.forEach(item -> newFoodList.add(item.getName()));

        List<Moves> animalMovesList = movesList.stream()
                .filter(item -> animal.getMoves().contains(item.getId()))
                .toList();
        List<String> newMovesList = new ArrayList<>();
        animalMovesList.forEach(item -> newMovesList.add(item.getName()));

        AnimalToShow newOne = new AnimalToShow(animal);
        newOne.setFoodList(newFoodList);
        newOne.setMovesList(newMovesList);
        return newOne;
    }

    public void updateAnimalToShowList(String category, Actionable animal) {
        AnimalToShow oldItem = this.getAnimalToShow(category, animal.getId());
        this.animalToShowList = animalToShowList.stream()
                .filter(item -> !Objects.equals(item, oldItem))
                .collect(Collectors.toList());
        AnimalToShow newItem = conversion(animal);
        this.animalToShowList.add(newItem);
    }

    public Actionable getAnimal(String category, Long id) {
        Actionable animal = null;
        switch (category) {
            case "Cat" -> animal = this.catList.stream().filter(item -> item.getId().equals(id)).findFirst().get();
            case "Dog" -> animal = this.dogList.stream().filter(item -> item.getId().equals(id)).findFirst().get();
            case "Camel" -> animal = this.camelList.stream().filter(item -> item.getId().equals(id)).findFirst().get();
            case "Horse" -> animal = this.horseList.stream().filter(item -> item.getId().equals(id)).findFirst().get();
            case "Donkey" ->
                    animal = this.donkeyList.stream().filter(item -> item.getId().equals(id)).findFirst().get();
            case "Humster" ->
                    animal = this.humsterList.stream().filter(item -> item.getId().equals(id)).findFirst().get();
        }
        return animal;
    }

    public AnimalToShow getAnimalToShow(String category, Long id) {
        return this.animalToShowList.stream()
                .filter(item -> item.getType().equals(category))
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .get();
    }

    public List<Cat> getCatList() {
        return catList;
    }

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
    }

    public List<Dog> getDogList() {
        return dogList;
    }

    public void setDogList(List<Dog> dogList) {
        this.dogList = dogList;
    }

    public List<Humster> getHumsterList() {
        return humsterList;
    }

    public void setHumsterList(List<Humster> humsterList) {
        this.humsterList = humsterList;
    }

    public List<Horse> getHorseList() {
        return horseList;
    }

    public void setHorseList(List<Horse> horseList) {
        this.horseList = horseList;
    }

    public List<Donkey> getDonkeyList() {
        return donkeyList;
    }

    public void setDonkeyList(List<Donkey> donkeyList) {
        this.donkeyList = donkeyList;
    }

    public List<Camel> getCamelList() {
        return camelList;
    }

    public void setCamelList(List<Camel> camelList) {
        this.camelList = camelList;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public List<Moves> getMovesList() {
        return movesList;
    }

    public void setMovesList(List<Moves> movesList) {
        this.movesList = movesList;
    }


}
