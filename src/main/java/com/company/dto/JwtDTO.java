package com.company.dto;

import com.company.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class JwtDTO {
    private UUID id;
    private Role role;

    public JwtDTO(UUID id, Role role) {
        this.id = id;
        this.role = role;
    }
}
