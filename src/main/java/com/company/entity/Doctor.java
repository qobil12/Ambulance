package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @JoinColumn(nullable = false, name = "region")
    @ManyToOne
    private Region region;

    @Column(nullable = false)
    private String phoneNumber;

}
