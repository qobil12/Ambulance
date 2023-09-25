package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application extends BaseEntity{

    @Column(nullable = false)
    private LocalDateTime created_date;
    @JoinColumn(nullable = false, name = "patient")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity patient;
    @JoinColumn(nullable = false,name = "region")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;
    @Column(nullable = false)
    private String fullAddress;
    @JoinColumn(nullable = false, name = "brigade")
    @ManyToOne(fetch = FetchType.LAZY)
    private Brigade brigade;
    @Column(nullable = false)
    private Boolean isClosed=false;
}
