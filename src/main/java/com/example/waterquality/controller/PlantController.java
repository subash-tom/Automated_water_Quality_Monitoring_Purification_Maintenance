package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.waterquality.model.Plant;
import com.example.waterquality.service.PlantService;

@Controller
@RequestMapping("/plants")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    // =====================================================
    // LIST
    // GET /plants
    // =====================================================

    @GetMapping
    public String plants(Model model) {

        model.addAttribute(
                "plants",
                plantService.getAllPlants()
        );

        return "plants";
    }

    // =====================================================
    // ADD FORM
    // GET /plants/new
    // =====================================================

    @GetMapping("/new")
    public String newPlant(Model model) {

        Plant plant = new Plant();

        plant.setMinimumPh(6.5);
        plant.setMaximumPh(8.5);

        model.addAttribute("plant", plant);

        return "plant-form";
    }

    // =====================================================
    // SAVE
    // POST /plants/save
    // =====================================================

    @PostMapping("/save")
    public String savePlant(
            @ModelAttribute("plant") Plant plant) {

        System.out.println("=================================");
        System.out.println("SAVE PLANT");
        System.out.println("Name     : " + plant.getName());
        System.out.println("Location : " + plant.getLocation());
        System.out.println("Min pH   : " + plant.getMinimumPh());
        System.out.println("Max pH   : " + plant.getMaximumPh());
        System.out.println("Current  : " + plant.getCurrentPh());
        System.out.println("=================================");

        if (plant.getMinimumPh() == null) {
            plant.setMinimumPh(6.5);
        }

        if (plant.getMaximumPh() == null) {
            plant.setMaximumPh(8.5);
        }

        // THIS ACTUALLY SAVES TO MYSQL
        plantService.createPlant(plant);

        return "redirect:/plants";
    }

    // =====================================================
    // EDIT FORM
    // GET /plants/edit/{id}
    // =====================================================

    @GetMapping("/edit/{id}")
    public String editPlant(
            @PathVariable Long id,
            Model model) {

        Plant plant = plantService.getPlantById(id);

        model.addAttribute("plant", plant);

        return "plant-edit";
    }

    // =====================================================
    // UPDATE
    // POST /plants/update/{id}
    // =====================================================

    @PostMapping("/update/{id}")
    public String updatePlant(
            @PathVariable Long id,
            @ModelAttribute("plant") Plant plant) {

        plantService.updatePlant(id, plant);

        return "redirect:/plants";
    }

    // =====================================================
    // DELETE
    // GET /plants/delete/{id}
    // =====================================================

    @GetMapping("/delete/{id}")
    public String deletePlant(
            @PathVariable Long id) {

        plantService.deletePlant(id);

        return "redirect:/plants";
    }
}