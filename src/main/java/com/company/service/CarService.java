package com.company.service;

import com.company.dto.CarDTO;
import com.company.entity.Car;
import com.company.enums.CarModel;
import com.company.repository.CarRepository;
import com.company.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    RegionRepository regionRepository;
    public void createCar(CarDTO dto) {
        Car car=new Car();
        car.setModel(CarModel.valueOf(dto.getModel().toUpperCase()));
        car.setRegion(regionRepository.getRegionByName(dto.getRegionName().toUpperCase()));
        car.setNumber(dto.getNumber());
        carRepository.save(car);
    }
}
