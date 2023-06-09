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
import ru.finalproject.animal_service.models.Food;
import ru.finalproject.animal_service.models.Moves;
import ru.finalproject.animal_service.models.animals.*;
import ru.finalproject.animal_service.models.animals.abstracts.Actionable;
import ru.finalproject.animal_service.services.entityServices.GeneralService;

@Controller
@RequestMapping("/")
public class MainController {
    private static String USER_NAME = "Default";
    private final GeneralService service;

    @Autowired
    public MainController(GeneralService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String hallo() {
        return "index";
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
        model.addAttribute("allAnimals", service.getAllAnimalsToShow());
        model.addAttribute("moves", service.getMoves());
        model.addAttribute("food", service.getFood());
        model.addAttribute("counts", service.getCounts());
        return "animals";
    }

    @GetMapping("/new")
    public String newAnimal(Model model) {
        model.addAttribute("username", USER_NAME);
        model.addAttribute("allAnimals", service.getAllAnimalsToShow());
        model.addAttribute("counts", service.getCounts());
        return "new";
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
            case "Cat" -> this.service.saveAnimal(new Cat(name), true);
            case "Dog" -> this.service.saveAnimal(new Dog(name), true);
            case "Humster" -> this.service.saveAnimal(new Humster(name), true);
            case "Camel" -> this.service.saveAnimal(new Camel(name, time), true);
            case "Horse" -> this.service.saveAnimal(new Horse(name, time), true);
            case "Donkey" -> this.service.saveAnimal(new Donkey(name, time), true);
        }
        model.addAttribute("allAnimals", service.getAllAnimalsToShow());
        model.addAttribute("counts", service.getCounts());
        return "animals";
    }

    @GetMapping("/newFoodMoves")
    public String newFoodMoves(Model model) {
        model.addAttribute("username", USER_NAME);
        model.addAttribute("moves", service.getMoves());
        model.addAttribute("food", service.getFood());
        model.addAttribute("counts", service.getCounts());
        return "newFoodMoves";
    }

    @PostMapping("/newFoodMoves")
    public String addFoodMoves(
            @RequestParam(value = "foodName", required = false) String foodName,
            @RequestParam(value = "moveName", required = false) String moveName,
            Model model
    ) {
        model.addAttribute("username", USER_NAME);
        model.addAttribute("allAnimals", service.getAllAnimalsToShow());
        model.addAttribute("counts", service.getCounts());
        if (foodName != null) {
            this.service.addFood(new Food(foodName));
        }
        if (moveName != null) {
            this.service.addMove(new Moves(moveName));
        }
        return "animals";
    }

    @PostMapping("/delFoodMoves")
    public String delFoodMoves(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "name", required = false) String name
    ) {
        switch (type) {
            case "food" -> this.service.delFood(name);
            case "moves" -> this.service.delMove(name);
        }
               return "redirect:newFoodMoves";
    }

    @GetMapping("/edit")
    public String configure(
            @RequestParam(value = "type") String animalType,
            @RequestParam(value = "id") Long id,
            Model model
    ) {
        switch (animalType) {
            case "Cat", "Dog", "Humster" -> model.addAttribute("type", 0);
            case "Camel", "Horse", "Donkey" -> model.addAttribute("type", 1);
               }
        model.addAttribute("animal", service.getAnimalToShow(animalType, id));
        model.addAttribute("moves", service.getMoves());
        model.addAttribute("food", service.getFood());
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(
            @RequestParam(value = "type") String animalType,
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "name", required = false) String animalName,
            @RequestParam(value = "foodId", required = false) Long foodId,
            @RequestParam(value = "moveId", required = false) Long moveId,
            @RequestParam(value = "action") String action,
            Model model
    ) {
        Actionable animal = service.getAnimal(animalType, id);
        model.addAttribute("username", USER_NAME);
        if (animalName != null) {
            animal.setName(animalName);
        }
        switch (action) {
            case "finish" -> {
                this.service.saveAnimal(animal, false);
                model.addAttribute("allAnimals", service.getAllAnimalsToShow());
                model.addAttribute("counts", service.getCounts());
                return "animals";
            }
            case "delete" -> {
                this.service.delAnimal(animal);
                model.addAttribute("allAnimals", service.getAllAnimalsToShow());
                model.addAttribute("counts", service.getCounts());
                return "animals";
            }
            case "addMove" -> this.service.animalAddMove(animal, moveId);
            case "delMove" -> this.service.animalDelMove(animal, moveId);
            case "addFood" -> this.service.animalAddFood(animal, foodId);
            case "delFood" -> this.service.animalDelFood(animal, foodId);
        }
        this.configure(animalType, id, model);
        return "edit";
    }
}
