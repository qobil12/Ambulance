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
@Getter
@Setter
public class PatientApplicationDTO {
    @NotNull(message = "Id mustn't be null")
    @NotBlank
    private UUID userId;
    @NotBlank
    @NotNull(message = "Address mustn't be null")
    private String address;
    @NotBlank
    @NotNull
    private String illness;
    @NotNull
    @NotBlank
    private Long latitude;
    @NotNull
    @NotBlank
    private Long longitude;
    @NotNull
    @NotBlank
    private String regionName;
}
