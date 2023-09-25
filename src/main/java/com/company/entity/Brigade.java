package com.company.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
public class Brigade extends BaseEntity {



    @Getter
    @JoinColumn(nullable = false,name = "doctor")
    @ManyToOne
    private Doctor doctor;

    @Getter
    @JoinColumn(nullable = false,name = "car")
    @ManyToOne
    private Car car;


    @Getter
    @JoinColumn(nullable = false,name = "region")
    @ManyToOne
    private Region region;

    private Boolean isBusy=false;

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Boolean getBusy() {
        return isBusy;
    }

    public void setBusy(Boolean busy) {
        isBusy = busy;
    }

}
