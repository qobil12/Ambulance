package com.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationInfoDTO {
    @NotNull
    @NotBlank
    private UUID brigadeId;
    @NotNull
    @NotBlank
    private String patientFullName;
    @NotNull
    @NotBlank
    private String regionName;
    @NotNull
    @NotBlank
    private String fullAddress;
    @NotNull
    @NotBlank
    @PastOrPresent
    private LocalDateTime createdDate;
    @NotNull
    @NotBlank
    private Boolean status;
}
