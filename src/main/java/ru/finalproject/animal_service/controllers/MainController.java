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
            case "Cat" -> this.service.addAnimal(new Cat(name));
            case "Dog" -> this.service.addAnimal(new Dog(name));
            case "Humster" -> this.service.addAnimal(new Humster(name));
            case "Camel" -> this.service.addAnimal(new Camel(name, time));
            case "Horse" -> this.service.addAnimal(new Horse(name, time));
            case "Donkey" -> this.service.addAnimal(new Donkey(name, time));
        }
        model.addAttribute("allAnimals", service.getAnimals());
        return "/animals";
    }

    @GetMapping("/edit")
    public String configure(
            @RequestParam(value = "type") String animalType,
            @RequestParam(value = "id") Long id,
            Model model
    ){
        model.addAttribute("type", animalType);
        model.addAttribute("animal", this.service.getAnimal(animalType, id));
        return "/edit";
    }
}
