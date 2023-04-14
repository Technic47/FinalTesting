package ru.finalproject.animal_service.controllers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.finalproject.animal_service.models.AnimalToShow;
import ru.finalproject.animal_service.models.Food;
import ru.finalproject.animal_service.models.Moves;
import ru.finalproject.animal_service.models.animals.*;
import ru.finalproject.animal_service.models.animals.abstracts.Actionable;
import ru.finalproject.animal_service.services.Cache;
import ru.finalproject.animal_service.services.entityServices.GeneralService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class MainController {
    private static String USER_NAME = "Default";
    private final GeneralService service;
    private Set<AnimalToShow> animals = new HashSet<>();

    private final Cache cache = new Cache();

    @Autowired
    public MainController(GeneralService service) {
        this.service = service;
        this.service.setCache(this.cache);
    }

    @GetMapping("/")
    public String hallo() {
        return "/index";
    }

    @PostMapping("/start")
    public String setName(
            @RequestParam(value = "name", required = false) String userName,
            Model model
    ) {
        if (!(userName == null)) {
            USER_NAME = userName;
        }

        model.addAttribute("username", USER_NAME);
        model.addAttribute("allAnimals", cache.getAnimals());
        model.addAttribute("moves", cache.getMovesList());
        model.addAttribute("food", cache.getFoodList());
        return "/animals";
    }

    private void updateAnimalToShowList() {
        List<Actionable> animalList = service.getAnimals();
        this.animals = new HashSet<>();
        List<Food> finalFoodList = service.getFood();
        List<Moves> finalMovesList = service.getMoves();
        animalList.forEach(animal -> {
            List<Food> animalFoodList = finalFoodList.stream()
                    .filter(item -> animal.getFood().contains(item.getId()))
                    .toList();

            List<String> newFoodList = new ArrayList<>();
            animalFoodList.forEach(item -> newFoodList.add(item.getName()));

            List<Moves> animalMovesList = finalMovesList.stream()
                    .filter(item -> animal.getMoves().contains(item.getId()))
                    .toList();

            List<String> newMovesList = new ArrayList<>();
            animalMovesList.forEach(item -> newMovesList.add(item.getName()));

            AnimalToShow newOne = new AnimalToShow(animal);
            newOne.setFoodList(newFoodList);
            newOne.setMovesList(newMovesList);
            animals.add(newOne);
        });
    }

    @GetMapping("/new")
    public String newAnimal(Model model) {
        model.addAttribute("username", USER_NAME);
        model.addAttribute("allAnimals", cache.getAnimals());
        return "/new";
    }

    @PostMapping("/new")
    public String create(
            @RequestParam(value = "name")
            @NotBlank(message = "Поле не должно быть пустым!")
            @Size(min = 1, max = 100)
            String name,
            @RequestParam(value = "time", required = false)
            @NotBlank(message = "Поле не должно быть пустым!")
            @Size(min = 1, max = 24)
            Integer time,
            @RequestParam(value = "type") String animalType,
            Model model
    ) {
        switch (animalType) {
            case "Cat" -> this.service.saveAnimal(new Cat(name));
            case "Dog" -> this.service.saveAnimal(new Dog(name));
            case "Humster" -> this.service.saveAnimal(new Humster(name));
            case "Camel" -> this.service.saveAnimal(new Camel(name, time));
            case "Horse" -> this.service.saveAnimal(new Horse(name, time));
            case "Donkey" -> this.service.saveAnimal(new Donkey(name, time));
        }
        model.addAttribute("allAnimals", cache.getAnimals());
        return "/animals";
    }

    @GetMapping("/newFoodMoves")
    public String newFoodMoves(Model model) {
        model.addAttribute("username", USER_NAME);
        model.addAttribute("allAnimals", cache.getAnimals());
        return "/newFoodMoves";
    }

    @PostMapping("/newFoodMoves")
    public String addFoodMoves(
            @RequestParam(value = "foodName", required = false) String foodName,
            @RequestParam(value = "moveName", required = false) String moveName,
            Model model
    ) {
        model.addAttribute("username", USER_NAME);
        model.addAttribute("allAnimals", cache.getAnimals());
        if (foodName != null) {
            this.service.addFood(foodName);
        }
        if (moveName != null) {
            this.service.addMove(moveName);
        }
        return "/animals";
    }

    @GetMapping("/edit")
    public String configure(
            @RequestParam(value = "type") String animalType,
            @RequestParam(value = "id") Long id,
            Model model
    ) {
        if (animalType.contains("Camel Horse Donkey")) {
            model.addAttribute("type", 1);
        } else {
            model.addAttribute("type", 0);
        }
        model.addAttribute("animal", cache.getAnimal(animalType, id));
        model.addAttribute("moves", cache.getMovesList());
        model.addAttribute("food", cache.getFoodList());
        return "/edit";
    }

    @PostMapping("/edit")
    public String edit(
            @RequestParam(value = "type") String animalType,
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "name", required = false) String animalName,
//            @RequestParam(value = "time", required = false) String animalTime,
            @RequestParam(value = "foodId", required = false) Long foodId,
            @RequestParam(value = "moveId", required = false) Long moveId,
            @RequestParam(value = "action") String action,
            Model model
    ) {
        Actionable animal = cache.getAnimal(animalType, id);
        model.addAttribute("username", USER_NAME);
        model.addAttribute("allAnimals", cache.getAnimals());
        if (animalName != null) {
            animal.setName(animalName);
        }
        switch (action) {
            case "finish" -> {
                this.service.saveAnimal(animal);
                return "/animals";
            }
            case "delete" -> {
                this.service.delAnimal(animal);
                return "/animals";
            }
            case "addMove" -> this.service.animalAddMove(animal, moveId);
            case "delMove" -> this.service.animalDelMove(animal, moveId);
            case "addFood" -> this.service.animalAddFood(animal, foodId);
            case "delFood" -> this.service.animalDelFood(animal, foodId);
        }
        this.configure(animalType, id, model);
        return "/edit";
    }
}
