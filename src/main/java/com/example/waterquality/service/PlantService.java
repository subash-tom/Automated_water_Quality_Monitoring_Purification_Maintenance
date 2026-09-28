package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.Plant;
import com.example.waterquality.repository.PlantRepository;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    // GET ALL
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    // GET BY ID
    public Plant getPlantById(Long id) {

        return plantRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Plant not found: " + id
                    )
                );
    }

    // CREATE
    public Plant createPlant(Plant plant) {

        if (plant.getMinimumPh() == null) {
            plant.setMinimumPh(6.5);
        }

        if (plant.getMaximumPh() == null) {
            plant.setMaximumPh(8.5);
        }

        return plantRepository.save(plant);
    }

    // UPDATE
    public Plant updatePlant(Long id, Plant plant) {

        Plant existing = getPlantById(id);

        existing.setName(plant.getName());
        existing.setLocation(plant.getLocation());
        existing.setMinimumPh(plant.getMinimumPh());
        existing.setMaximumPh(plant.getMaximumPh());
        existing.setCurrentPh(plant.getCurrentPh());

        return plantRepository.save(existing);
    }

    // DELETE
    public void deletePlant(Long id) {

        plantRepository.deleteById(id);
    }
}