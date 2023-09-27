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
public class BrigadeDTO {
    @NotNull
    @NotBlank
    private UUID carId;
    @NotNull
    @NotBlank
    private UUID regionId;
    @NotNull
    @NotBlank
    private UUID doctorId;

}
