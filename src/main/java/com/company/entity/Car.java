package com.company.entity;

import com.company.enums.CarModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;
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
}
