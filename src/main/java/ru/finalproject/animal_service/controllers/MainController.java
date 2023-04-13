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
import ru.finalproject.animal_service.models.animals.*;
import ru.finalproject.animal_service.models.animals.abstracts.Actionable;
import ru.finalproject.animal_service.services.GeneralService;

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
        model.addAttribute("allAnimals", service.getAnimals());
        return "/animals";
    }

    @GetMapping("/new")
    public String newAnimal(Model model) {
        model.addAttribute("username", USER_NAME);
        model.addAttribute("allAnimals", service.getAnimals());
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
        model.addAttribute("allAnimals", service.getAnimals());
        return "/animals";
    }

    @GetMapping("/newFoodMoves")
    public String newFoodMoves(Model model) {
        model.addAttribute("username", USER_NAME);
        model.addAttribute("allAnimals", service.getAnimals());
        return "/newFoodMoves";
    }

    @PostMapping("/newFoodMoves")
    public String addFoodMoves(
            @RequestParam(value = "foodName", required = false) String foodName,
            @RequestParam(value = "moveName", required = false) String moveName,
            Model model
    ) {
        model.addAttribute("username", USER_NAME);
        model.addAttribute("allAnimals", service.getAnimals());
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
        model.addAttribute("animal", this.service.getAnimal(animalType, id));
        model.addAttribute("moves", service.getMoves());
        model.addAttribute("food", service.getFood());
        return "/edit";
    }

    @PostMapping("/edit")
    public void edit(
            @RequestParam(value = "type") String animalType,
            @RequestParam(value = "id") Long animalId,
            @RequestParam(value = "foodId", required = false) Long foodId,
            @RequestParam(value = "moveId", required = false) Long moveId,
            @RequestParam(value = "action") String action,
            Model model
    ) {
        Actionable animal = service.getAnimal(animalType, animalId);
        switch (action) {
            case "finish" -> this.service.saveAnimal(animal);
            case "addMove" -> this.service.animalAddMove(animal, moveId);
            case "delMove" -> this.service.animalDelMove(animal, moveId);
            case "addFood" -> this.service.animalAddFood(animal, foodId);
            case "delFood" -> this.service.animalDelFood(animal, foodId);
        }
        this.configure(animalType, animalId, model);
    }
}
