package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    private String brigadeId;
    private String patientId;
    private String regionName;
    private String fullAddress;

}
