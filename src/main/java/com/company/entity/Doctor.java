package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends BaseEntity {


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
