package com.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brigade  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(nullable = false,name = "doctor")
    @ManyToOne
    private Doctor doctor;

    @JoinColumn(nullable = false,name = "car")
    @ManyToOne
    private Car car;

    @JoinColumn(nullable = false,name = "region")
    @ManyToOne
    private Region region;

    private Boolean isBusy=false;

}
