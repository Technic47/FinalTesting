package ru.finalproject.animal_service.services.entityServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.finalproject.animal_service.models.Food;
import ru.finalproject.animal_service.models.Moves;
import ru.finalproject.animal_service.models.animals.*;
import ru.finalproject.animal_service.models.animals.abstracts.Actionable;
import ru.finalproject.animal_service.services.Cache;

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
    private CounterService counterService;
    private Cache cache;

    public GeneralService() {
        this.loadValues();
    }

    private void loadValues() {
        this.cache.setCatList(catService.index());
        this.cache.setDogList(dogService.index());
        this.cache.setHumsterList(humsterService.index());
        this.cache.setHorseList(horseService.index());
        this.cache.setDonkeyList(donkeyService.index());
        this.cache.setCamelList(camelService.index());
        this.cache.setMovesList(movesService.index());
        this.cache.setFoodList(foodService.index());
    }

    public void saveAnimal(Actionable animal) {
        String type = animal.getClass().getSimpleName();
        Actionable newAnimal = null;
        switch (type) {
            case "Cat" -> newAnimal = this.catService.save((Cat) animal);
            case "Dog" -> newAnimal = this.dogService.save((Dog) animal);
            case "Camel" -> newAnimal = this.camelService.save((Camel) animal);
            case "Horse" -> newAnimal = this.horseService.save((Horse) animal);
            case "Donkey" -> newAnimal = this.donkeyService.save((Donkey) animal);
            case "Humster" -> newAnimal = this.humsterService.save((Humster) animal);
        }
        this.counterService.countPlus(type);
        this.cache.addAnimalToCache(type, newAnimal);
    }

    public void delAnimal(Actionable animal) {
        String type = animal.getClass().getSimpleName();
        Long id = animal.getId();
        switch (type) {
            case "Cat" -> this.catService.delete(id);
            case "Dog" -> this.dogService.delete(id);
            case "Camel" -> this.camelService.delete(id);
            case "Horse" -> this.horseService.delete(id);
            case "Donkey" -> this.donkeyService.delete(id);
            case "Humster" -> this.humsterService.delete(id);
        }
        this.counterService.countMinus(type);
        this.cache.delAnimalFromCache(type, id);
    }

    @Deprecated
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

    @Deprecated
    public Actionable getAnimal(String category, Long id) {
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

    @Deprecated
    public List<Moves> getMoves() {
        return movesService.index();
    }

    @Deprecated
    public List<Food> getFood() {
        return foodService.index();
    }

    public void addMove(String name) {
        Moves item = new Moves(name);
        this.movesService.save(item);
        this.cache.addMovesToCache(item);
    }

    public boolean delMove(String name) {
        List<Moves> findItem = this.cache.getMovesList();
        Optional<Moves> findMove = findItem.stream().filter(item -> item.getName().equals(name)).findFirst();
        if (findMove.isPresent()) {
            this.movesService.delete(findMove.get().getId());
            this.cache.delMovesFromCache(findMove.get());
            return true;
        } else {
            return false;
        }
    }

    public void addFood(String name) {
        Food item = new Food(name);
        this.foodService.save(item);
        this.cache.addFoodToCache(item);
    }

    public boolean delFood(String name) {
        List<Food> movesList = this.cache.getFoodList();
        Optional<Food> findItem = movesList.stream().filter(item -> item.getName().equals(name)).findFirst();
        if (findItem.isPresent()) {
            this.foodService.delete(findItem.get().getId());
            this.cache.delFoodFromCache(findItem.get());
            return true;
        } else {
            return false;
        }
    }

    public void animalAddMove(Actionable animal, Long move) {
        String type = animal.getClass().getSimpleName();
        switch (type) {
            case "Cat" -> this.catService.addToMovesList((Cat) animal, move);
            case "Dog" -> this.dogService.addToMovesList((Dog) animal, move);
            case "Camel" -> this.camelService.addToMovesList((Camel) animal, move);
            case "Horse" -> this.horseService.addToMovesList((Horse) animal, move);
            case "Donkey" -> this.donkeyService.addToMovesList((Donkey) animal, move);
            case "Humster" -> this.humsterService.addToMovesList((Humster) animal, move);
        }
        this.cache.updateAnimalInCache(type, animal);
    }

    public void animalDelMove(Actionable animal, Long move) {
        String type = animal.getClass().getSimpleName();
        switch (type) {
            case "Cat" -> this.catService.delFromMovesList((Cat) animal, move);
            case "Dog" -> this.dogService.delFromMovesList((Dog) animal, move);
            case "Camel" -> this.camelService.delFromMovesList((Camel) animal, move);
            case "Horse" -> this.horseService.delFromMovesList((Horse) animal, move);
            case "Donkey" -> this.donkeyService.delFromMovesList((Donkey) animal, move);
            case "Humster" -> this.humsterService.delFromMovesList((Humster) animal, move);
        }
        this.cache.updateAnimalInCache(type, animal);
    }

    public void animalAddFood(Actionable animal, Long id) {
        String type = animal.getClass().getSimpleName();
        switch (type) {
            case "Cat" -> this.catService.addToFoodList((Cat) animal, id);
            case "Dog" -> this.dogService.addToFoodList((Dog) animal, id);
            case "Camel" -> this.camelService.addToFoodList((Camel) animal, id);
            case "Horse" -> this.horseService.addToFoodList((Horse) animal, id);
            case "Donkey" -> this.donkeyService.addToFoodList((Donkey) animal, id);
            case "Humster" -> this.humsterService.addToFoodList((Humster) animal, id);
        }
        this.cache.updateAnimalInCache(type, animal);
    }

    public void animalDelFood(Actionable animal, Long id) {
        String type = animal.getClass().getSimpleName();
        switch (type) {
            case "Cat" -> this.catService.delFromFoodList((Cat) animal, id);
            case "Dog" -> this.dogService.delFromFoodList((Dog) animal, id);
            case "Camel" -> this.camelService.delFromFoodList((Camel) animal, id);
            case "Horse" -> this.horseService.delFromFoodList((Horse) animal, id);
            case "Donkey" -> this.donkeyService.delFromFoodList((Donkey) animal, id);
            case "Humster" -> this.humsterService.delFromFoodList((Humster) animal, id);
        }
        this.cache.updateAnimalInCache(type, animal);
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

    @Autowired
    private void setCounterService(CounterService counterService) {
        this.counterService = counterService;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }
}