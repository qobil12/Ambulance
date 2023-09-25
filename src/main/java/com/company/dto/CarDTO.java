package com.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDTO {
    @NotNull
    @NotBlank
    private String model;
    @Email(regexp = "01 \\| \\d{3} [A-Z]{3}")
    @NotNull
    @NotBlank
    private String number;
    @NotNull
    @NotBlank
    private UUID regionId;
}
