package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.Plant;
import com.example.waterquality.model.WaterMetric;
import com.example.waterquality.repository.PlantRepository;
import com.example.waterquality.repository.WaterMetricRepository;

@Service
public class MetricService {

    private final WaterMetricRepository metricRepository;
    private final PlantRepository plantRepository;

    public MetricService(
            WaterMetricRepository metricRepository,
            PlantRepository plantRepository) {

        this.metricRepository = metricRepository;
        this.plantRepository = plantRepository;
    }

    // Add water quality metric
    public WaterMetric addMetric(
            Long plantId,
            WaterMetric metric) {

        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() ->
                        new RuntimeException("Plant not found"));

        // Set plant
        metric.setPlant(plant);

        /*
         * pH safety rule:
         * Normal range = 6.5 to 8.5
         *
         * If pH is outside this range,
         * status automatically becomes WARNING.
         */

        if (metric.getPh() < 6.5 || metric.getPh() > 8.5) {
            metric.setStatus("WARNING");
        } else {
            metric.setStatus("NORMAL");
        }

        return metricRepository.save(metric);
    }

    // Get all metrics
    public List<WaterMetric> getAllMetrics() {
        return metricRepository.findAll();
    }

    // Get metrics for a plant
    public List<WaterMetric> getMetricsByPlant(Long plantId) {

        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() ->
                        new RuntimeException("Plant not found"));

        return metricRepository.findByPlant(plant);
    }

    // Get warning metrics
    public List<WaterMetric> getWarnings() {
        return metricRepository.findByStatus("WARNING");
    }
}