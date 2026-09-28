package com.example.waterquality.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "water_metrics")
public class WaterMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double ph;

    private double turbidity;

    private String status;

    private LocalDateTime recordedAt;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;

    public WaterMetric() {
    }

    public Long getId() {
        return id;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(double turbidity) {
        this.turbidity = turbidity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}