package ru.finalproject.animal_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.finalproject.animal_service.models.Food;
import ru.finalproject.animal_service.models.Moves;
import ru.finalproject.animal_service.models.animals.*;
import ru.finalproject.animal_service.models.animals.abstracts.Actionable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class GeneralService {
    private CatService catService;
    private DogService dogService;
    private CamelService camelService;
    private DonkeyService donkeyService;
    private HorseService horseService;
    private HumsterService humsterService;
    private MovesService movesService;
    private FoodService foodService;

    public void addAnimal(Actionable animal) {
        switch (animal.getClass().getSimpleName()) {
            case "Cat" -> this.catService.save((Cat) animal);
            case "Dog" -> this.dogService.save((Dog) animal);
            case "Camel" -> this.camelService.save((Camel) animal);
            case "Horse" -> this.horseService.save((Horse) animal);
            case "Donkey" -> this.donkeyService.save((Donkey) animal);
            case "Humster" -> this.humsterService.save((Humster) animal);
        }
    }

    public void delAnimal(Actionable animal) {
        switch (animal.getClass().getSimpleName()) {
            case "Cat" -> this.catService.delete(animal.getId());
            case "Dog" -> this.dogService.delete(animal.getId());
            case "Camel" -> this.camelService.delete(animal.getId());
            case "Horse" -> this.horseService.delete(animal.getId());
            case "Donkey" -> this.donkeyService.delete(animal.getId());
            case "Humster" -> this.humsterService.delete(animal.getId());
        }
    }

    public List<Actionable> getAnimals() {
        List<Actionable> newList = new ArrayList<>();
        newList.addAll(this.catService.index());
        newList.addAll(this.dogService.index());
        newList.addAll(this.camelService.index());
        newList.addAll(this.horseService.index());
        newList.addAll(this.donkeyService.index());
        newList.addAll(this.humsterService.index());
        return newList;
    }

    public Actionable getAnimal(String category, Long id){
        Actionable animal = null;
        switch (category) {
            case "Cat" -> animal = this.catService.show(id);
            case "Dog" -> animal = this.dogService.show(id);
            case "Camel" -> animal = this.camelService.show(id);
            case "Horse" -> animal = this.horseService.show(id);
            case "Donkey" -> animal = this.donkeyService.show(id);
            case "Humster" -> animal = this.humsterService.show(id);
        }
        return animal;
    }

    public void addMove(String name) {
        this.movesService.save(new Moves(name));
    }

    public boolean delMove(String name) {
        List<Moves> movesList = movesService.index();
        Optional<Moves> findMove = movesList.stream().filter(item -> item.getName().equals(name)).findFirst();
        if (findMove.isPresent()) {
            this.movesService.delete(findMove.get().getId());
            return true;
        } else {
            return false;
        }
    }

    public void addFood(String name) {
        this.foodService.save(new Food(name));
    }

    public boolean delFood(String name) {
        List<Food> movesList = foodService.index();
        Optional<Food> findMove = movesList.stream().filter(item -> item.getName().equals(name)).findFirst();
        if (findMove.isPresent()) {
            this.foodService.delete(findMove.get().getId());
            return true;
        } else {
            return false;
        }
    }


    @Autowired
    private void setCatService(CatService catService) {
        this.catService = catService;
    }

    @Autowired
    private void setDogService(DogService dogService) {
        this.dogService = dogService;
    }

    @Autowired
    private void setCamelService(CamelService camelService) {
        this.camelService = camelService;
    }

    @Autowired
    private void setDonkeyService(DonkeyService donkeyService) {
        this.donkeyService = donkeyService;
    }

    @Autowired
    private void setHorseService(HorseService horseService) {
        this.horseService = horseService;
    }

    @Autowired
    private void setHumsterService(HumsterService humsterService) {
        this.humsterService = humsterService;
    }

    @Autowired
    private void setMovesService(MovesService movesService) {
        this.movesService = movesService;
    }

    @Autowired
    private void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }
}
