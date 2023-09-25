package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientApplication extends BaseEntity {

    @Column(nullable = false)
    private String userId;
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
    private String regionName;
}
