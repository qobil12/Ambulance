package com.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrigadeDTO {
    @Email(regexp = "01 \\| \\d{3} [A-Za-z]{3}")
    @NotNull
    @NotBlank
    private String carNumber;
    @NotNull
    @NotBlank
    private String regionName;
    @NotNull
    @NotBlank
    @Email(regexp = "\\+998\\d{9}")
    private String doctorPhoneNumber;

}
