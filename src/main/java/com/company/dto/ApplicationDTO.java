package com.company.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationDTO {
    private String brigadeId;
    private String patientApplicationId;
}
