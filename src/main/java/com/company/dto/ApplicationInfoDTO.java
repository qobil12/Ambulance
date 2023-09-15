package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationInfoDTO {
    private String brigadeId;
    private String patientFullName;
    private String regionName;
    private String fullAddress;
    private LocalDateTime createdDate;
    private Boolean status;
}
