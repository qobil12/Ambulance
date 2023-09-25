package com.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDoctorInfoDTO {
    @NotBlank
    @NotNull
    private UUID doctorId;
    private String name;
    private String surname;
    private UUID region;
    private UUID brigade;
    private String phoneNumber;
}