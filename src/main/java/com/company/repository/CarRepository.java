package com.company.repository;

import com.company.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    Car getByNumber(String number);

    List<Car> findAllByBrigadeNull();

    boolean existsByNumber(String number);

}
