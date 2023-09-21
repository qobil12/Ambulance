package com.company.service;

import com.company.dto.CarDTO;
import com.company.dto.ChangeCarNumberDTO;
import com.company.entity.Car;
import com.company.enums.CarModel;
import com.company.exceptions.ItemNotFoundException;
import com.company.repository.CarRepository;
import com.company.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    final
    CarRepository carRepository;
    final
    RegionRepository regionRepository;

    private CarService(CarRepository carRepository, RegionRepository regionRepository) {
        this.carRepository = carRepository;
        this.regionRepository = regionRepository;
    }

    public void createCar(CarDTO dto) {
        Car car = new Car();
        car.setModel(CarModel.valueOf(dto.getModel().toUpperCase()));
        car.setRegion(regionRepository.getRegionByName(dto.getRegionName().toUpperCase()));
        car.setNumber(dto.getNumber());
        carRepository.save(car);
    }

    public List<CarDTO> getAllFreeCars() {
        return carRepository.findAllByBrigadeNull().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private CarDTO toDTO(Car car){
        return CarDTO.builder().number(car.getNumber())
                .regionName(car.getRegion().getName())
                .model(car.getModel().name()).build();
    }

    public String deleteById(String id) {
        if(!carRepository.existsById(id)){
            throw new ItemNotFoundException("Car with this id not found");
        }
        carRepository.deleteById(id);
        return "Car successfully deleted !";
    }

    public String changeNumber(ChangeCarNumberDTO dto) {
        Optional<Car> byId = carRepository.findById(dto.getId());
        if(byId.isEmpty()){
            throw new ItemNotFoundException("Car not found with this id");
        }
        Car car = byId.get();
        car.setNumber(dto.getNewNumber());
        carRepository.save(car);
        return "Car's number successfully changed.";
    }
}
