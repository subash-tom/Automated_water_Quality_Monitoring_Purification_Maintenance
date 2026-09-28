package com.example.waterquality.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "minimum_ph")
    private Double minimumPh;

    @Column(name = "maximum_ph")
    private Double maximumPh;

    @Column(name = "current_ph")
    private Double currentPh;

    public Plant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getMinimumPh() {
        return minimumPh;
    }

    public void setMinimumPh(Double minimumPh) {
        this.minimumPh = minimumPh;
    }

    public Double getMaximumPh() {
        return maximumPh;
    }

    public void setMaximumPh(Double maximumPh) {
        this.maximumPh = maximumPh;
    }

    public Double getCurrentPh() {
        return currentPh;
    }

    public void setCurrentPh(Double currentPh) {
        this.currentPh = currentPh;
    }

    @Transient
    public String getAlertStatus() {

        if (currentPh == null) {
            return "NO DATA";
        }

        double min = minimumPh != null ? minimumPh : 6.5;
        double max = maximumPh != null ? maximumPh : 8.5;

        if (currentPh < min) {
            return "LOW pH";
        }

        if (currentPh > max) {
            return "HIGH pH";
        }

        return "NORMAL";
    }

    @Transient
    public String getAlertClass() {

        String status = getAlertStatus();

        if ("LOW pH".equals(status) ||
            "HIGH pH".equals(status)) {
            return "danger";
        }

        if ("NO DATA".equals(status)) {
            return "warning";
        }

        return "success";
    }
}