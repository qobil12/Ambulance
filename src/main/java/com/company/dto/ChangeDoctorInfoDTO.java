package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDoctorInfoDTO {
    private String doctorId;
    private String name;
    private String surname;
    private String region;
    private String brigade;
    private String phoneNumber;
}