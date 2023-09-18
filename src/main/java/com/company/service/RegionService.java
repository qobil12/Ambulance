package com.company.service;

import com.company.dto.RegionDTO;
import com.company.entity.Region;
import com.company.repository.RegionRepository;
import org.springframework.stereotype.Service;

@Service
public class RegionService {
    final
    RegionRepository repository;

    public RegionService(RegionRepository repository) {
        this.repository = repository;
    }

    public void createRegion(RegionDTO dto) {
        Region region = new Region();
        region.setName(dto.getName().toUpperCase());
        repository.save(region);
    }
}
