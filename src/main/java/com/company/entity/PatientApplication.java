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
public class PatientApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String illness;

    @Column(nullable = false)
    private Long latitude;

    @Column(nullable = false)
    private Long longitude;
    @Column(nullable = false)
    private Boolean isAttached = false;

    @Column(nullable = false)
    private UUID regionId;

}
