package com.company.service;

import com.company.dto.CarDTO;
import com.company.dto.ChangeCarNumberDTO;
import com.company.entity.Car;
import com.company.exceptions.ItemAlreadyExistsException;
import com.company.exceptions.ItemNotFoundException;
import com.company.mapper.CarMapper;
import com.company.repository.CarRepository;
import com.company.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService {
    final
    CarRepository carRepository;
    final
    RegionRepository regionRepository;
    final CarMapper carMapper=CarMapper.INSTANCE;

    private CarService(CarRepository carRepository, RegionRepository regionRepository) {
        this.carRepository = carRepository;
        this.regionRepository = regionRepository;
    }

    public CarDTO createCar(CarDTO dto) {
        if(carRepository.existsByNumber(dto.getNumber())){
            throw new ItemAlreadyExistsException("Car with this number already exists");
        }
//        Car car = new Car();
//        car.setModel(CarModel.valueOf(dto.getModel().toUpperCase()));
//        car.setRegion(regionRepository.getRegionByName(dto.getRegionId()));
//        car.setNumber(dto.getNumber());
//
      return carMapper.toCarDTO(carRepository.save(carMapper.toCarEntity(dto)));
    }

    public List<CarDTO> getAllFreeCars() {
        return carRepository.findAllByBrigadeNull().stream()
                .map(carMapper::toCarDTO)
                .collect(Collectors.toList());
    }

//    private CarDTO toDTO(Car car){
//        return CarDTO.builder().number(car.getNumber())
//                .regionId(car.getRegion().getName())
//                .model(car.getModel().name()).build();
//    }

    public void deleteById(UUID id) {
        carRepository.delete(carRepository.findById(id).orElseThrow(()-> new ItemNotFoundException("Car doesn't exist with this ID !")));
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
