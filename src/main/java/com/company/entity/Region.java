package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Region extends BaseEntity{

    @Column(nullable = false)
    private String name;
}
