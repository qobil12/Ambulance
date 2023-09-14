package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Brigade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;
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
