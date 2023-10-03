package com.company.controller;

import com.company.dto.CarDTO;
import com.company.dto.ChangeCarNumberDTO;
import com.company.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/car/")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Creates a car.",description = "Creates a new car entity.")
    @PostMapping("/create")
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO dto) {
        return ResponseEntity.ok().body(carService.createCar(dto));
    }

    @Operation(summary = "Get all free cars", description = "By this method you can get list of all cars that doesn't have brigade .")
    @GetMapping("/get_all_free")
    public ResponseEntity<List<CarDTO>> getAllFreeCars() {
        return ResponseEntity.ok().body(carService.getAllFreeCars());
    }
    @Operation(summary = "Delete car",description = "You can delete car by  this method giving id")
    @DeleteMapping("/delete_car/{id}")
    public void deleteCarById(@PathVariable UUID id){
       carService.deleteById(id);
    }
    @Operation(summary = "Change car's number",description = "By this method admin can change car's number.")
    @PutMapping("/change_number")
    public ResponseEntity<CarDTO> changeCarNumber(@RequestBody ChangeCarNumberDTO dto){
        return ResponseEntity.ok().body(carService.changeNumber(dto));
    }
}
