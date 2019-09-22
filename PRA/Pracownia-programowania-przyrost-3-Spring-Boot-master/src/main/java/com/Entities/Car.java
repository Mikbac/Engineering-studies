package com.Entities;

import javax.persistence.*;

@Entity
@Table(name = "car_table")

public class Car {
    @Id
    @GeneratedValue
    private long carId;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "productionDate", nullable = false)
    private int productionDate;

    public Car() {
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(int productionDate) {
        this.productionDate = productionDate;
    }
}