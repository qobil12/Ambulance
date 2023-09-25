package com.company.dto;

import jakarta.validation.constraints.Email;
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
public class BrigadeDoctorDTO {
    @NotBlank
    @NotNull
    private UUID brigadeId;
    @Email(regexp = "\\+998\\d{9}")
    @NotNull
    @NotBlank
    private String DoctorPhoneNumber;
}
