package com.company.dto;

import com.company.enums.CarModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDTO {
    private String model;

    private String number;
    private String regionName;

}
