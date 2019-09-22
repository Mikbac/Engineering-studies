package com.Entities;

import javax.persistence.*;

@Entity
public class Supplier {

    @Id
    @GeneratedValue
    @Column(name = "supplierId")
    private long supplierId;

    @Column(name = "name")
    private String name;

    @Column(name = "establishment")
    private int establishment;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bossId", referencedColumnName = "bossId")
    Boss boss;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carId", referencedColumnName = "carId")
    Car car;

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEstablishment() {
        return establishment;
    }

    public void setEstablishment(int establishment) {
        this.establishment = establishment;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}