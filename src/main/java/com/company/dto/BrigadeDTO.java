package com.company.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrigadeDTO {
    private String carNumber;
    private String regionName;
    private String doctorPhoneNumber;

}
