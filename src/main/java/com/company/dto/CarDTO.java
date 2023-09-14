package com.company.dto;

import com.company.enums.CarModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private String model;

    private String number;
    private String regionName;

}
