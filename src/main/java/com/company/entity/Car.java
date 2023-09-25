package com.company.entity;

import com.company.enums.CarModel;
import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarModel model;
    @Column(nullable = false)
    private String number;
    @JoinColumn(nullable = false, name = "region")
    @ManyToOne
    private Region region;
    @JoinColumn(name = "brigade")
    @OneToOne
    private Brigade brigade;

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }
}
