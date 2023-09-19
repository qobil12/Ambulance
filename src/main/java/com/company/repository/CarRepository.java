package com.company.repository;

import com.company.entity.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, String> {
    Car getByNumber(String number);
    List<Car> findAllByBrigadeNull();
}
