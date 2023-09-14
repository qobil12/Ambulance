package com.company.repository;

import com.company.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, String> {
    Car getByNumber(String number);
}
