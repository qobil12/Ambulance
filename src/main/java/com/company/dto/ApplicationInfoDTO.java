package com.company.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationInfoDTO {
    private String brigadeId;
    private String patientFullName;
    private String regionName;
    private String fullAddress;
    private LocalDateTime createdDate;
    private Boolean status;
}
