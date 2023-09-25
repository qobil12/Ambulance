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
public class BrigadeCarDTO {
    @NotNull
    @NotBlank
    @Email(regexp = "01 \\| \\d{3} [A-Z]{3}")
    private String carNumber;
    @NotNull
    @NotBlank
    private UUID brigadeId;
}
