package com.company.service;

import com.company.dto.CarDTO;
import com.company.entity.Car;
import com.company.enums.CarModel;
import com.company.repository.CarRepository;
import com.company.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    final
    CarRepository carRepository;
    final
    RegionRepository regionRepository;

    public CarService(CarRepository carRepository, RegionRepository regionRepository) {
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

    public List<Car> getAllFreeCars() {
        return carRepository.findAllByBrigadeNull();
    }
}
