package com.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime created_date;

    @JoinColumn(nullable = false,name = "patient")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

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
