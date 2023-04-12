package ru.finalproject.animal_service.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public String newAnimal(Model model) {
        model.addAttribute("username", USER_NAME);
        model.addAttribute("newCat", new Cat());
        model.addAttribute("newDog", new Dog());
        model.addAttribute("newHumster", new Humster());
        model.addAttribute("newHorse", new Horse());
        model.addAttribute("newDonkey", new Donkey());
        model.addAttribute("newCamel", new Camel());
        return "/new";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("newCat") Cat newCat,

    ){

        return "/animals";
    }
}
