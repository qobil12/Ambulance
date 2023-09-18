package com.company.controller;

import com.company.dto.CarDTO;
import com.company.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(name = "/create")
    private ResponseEntity<String> createCar(@RequestBody CarDTO dto){
        carService.createCar(dto);
        return ResponseEntity.ok().body("Car succesfully created !");
    }
}
