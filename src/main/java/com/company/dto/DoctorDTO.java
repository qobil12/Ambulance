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
public class DoctorDTO {
    @NotNull
    private UUID regionId;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String surname;
    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\+998\\d{9}$", message = "Invalid phone number format")
    private String phoneNumber;
}