package com.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeCarNumberDTO {
    @NotBlank
    @NotNull
    private UUID id;
    @Pattern(regexp = "01 \\| \\d{3} [A-Z]{3}")
    @NotNull
    private String newNumber;
}
