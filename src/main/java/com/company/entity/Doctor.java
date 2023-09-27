package com.company.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @JoinColumn(nullable = false, name = "region")
    @ManyToOne
    private Region region;

    @Column(nullable = false)
    private String phoneNumber;

    @JoinColumn(name = "brigade")
    @ManyToOne
    private Brigade brigade;
}
