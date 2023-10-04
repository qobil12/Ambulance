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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService {
    final
    CarRepository carRepository;
    final
    RegionRepository regionRepository;
    final CarMapper carMapper = CarMapper.INSTANCE;

    private CarService(CarRepository carRepository, RegionRepository regionRepository) {
        this.carRepository = carRepository;
        this.regionRepository = regionRepository;
    }

    public CarDTO createCar(CarDTO dto) {
        if (carRepository.existsByNumber(dto.getNumber())) {
            throw new ItemAlreadyExistsException("Car with this number already exists");
        }
        return carMapper.toCarDTO(carRepository.save(carMapper.toCarEntity(dto)));
    }

    public List<CarDTO> getAllFreeCars() {
        return carRepository.findAllByBrigadeNull().stream()
                .map(carMapper::toCarDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(UUID id) {
        carRepository.delete(carRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Car doesn't exist with this ID !")));
    }

    public CarDTO changeNumber(ChangeCarNumberDTO dto) {
        Car car = carRepository.findById(dto.getId()).orElseThrow(() -> new ItemNotFoundException("Car not found with this ID"));
        if (!car.getNumber().isEmpty()) car.setNumber(dto.getNewNumber());

        return carMapper.toCarDTO(carRepository.save(car));
    }
}
