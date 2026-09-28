package com.example.waterquality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.waterquality.model.Plant;
import com.example.waterquality.model.WaterMetric;

public interface WaterMetricRepository
        extends JpaRepository<WaterMetric, Long> {

    List<WaterMetric> findByPlant(Plant plant);

    List<WaterMetric> findByStatus(String status);
}