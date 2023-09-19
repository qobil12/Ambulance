package com.company.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDTO {
    private String regionName;
    private String name;
    private String surname;
    private String phoneNumber;

}
