package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientApplicationDTO {
    private String userId;
    private String address;
    private String illness;
    private Long latitude;
    private Long longitude;
    private String regionName;
}
