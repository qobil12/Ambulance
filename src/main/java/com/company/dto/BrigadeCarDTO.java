package com.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BrigadeCarDTO {
    @NotNull
    @NotBlank
    private UUID carId;
    @NotNull
    @NotBlank
    private UUID brigadeId;
}
