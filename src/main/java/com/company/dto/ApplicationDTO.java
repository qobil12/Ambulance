package com.company.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationDTO {
    @NotNull(message = "Brigade id mustn't be null")
    private UUID brigadeId;
    private String patientApplicationId;
}
