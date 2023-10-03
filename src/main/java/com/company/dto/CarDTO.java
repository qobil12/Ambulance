package com.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "01 \\| \\d{3} [A-Z]{3}")
    @NotNull
    @NotBlank
    private String number;
    @NotNull
    @NotBlank
    private UUID regionId;
}
