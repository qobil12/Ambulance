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
public class RegionChangeDTO {
    @NotNull(message = "Id mustn't be null")
    @NotBlank
    private UUID id;
    @NotBlank
    @NotNull(message = "Name mustn't be null")
    private String name;
}
