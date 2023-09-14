package com.company.repository;

import com.company.entity.Region;
import org.springframework.data.repository.CrudRepository;

public interface RegionRepository extends CrudRepository<Region,String> {
    Region getRegionById(String id);
    Region getRegionByName(String name);
}
