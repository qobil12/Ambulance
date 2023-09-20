package com.company.controller;

import com.company.dto.CarDTO;
import com.company.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCar(@RequestBody CarDTO dto) {
        carService.createCar(dto);
        return ResponseEntity.ok().body("Car succesfully created !");
    }

    @Operation(summary = "Get all free cars", description = "By this method you can get list of all cars that doesn't have brigade .")
    @GetMapping("/get_all_free")
    public ResponseEntity<List<CarDTO>> getAllFreeCars() {
        return ResponseEntity.ok().body(carService.getAllFreeCars());
    }
    @Operation(summary = "Delete car",description = "You can delete car by  this method giving id")
    @DeleteMapping("/delete_car/{id}")
    public ResponseEntity<String> deleteCarById(@PathVariable String id){
        return ResponseEntity.ok().body(carService.deleteById(id));
    }
}
