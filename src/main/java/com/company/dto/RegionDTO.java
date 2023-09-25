package com.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionDTO {
    private UUID id;
    @NotNull(message = "Name mustn't be null")
    @NotBlank
    private String name;
}
