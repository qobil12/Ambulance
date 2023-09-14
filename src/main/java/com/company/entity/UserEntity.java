package com.company.entity;

import com.company.enums.Role;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false,unique = true)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role= Role.USER;

}
