package com.company.service;

import com.company.dto.RegionChangeDTO;
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

    public String delete(String id) {
        repository.deleteById(id);
        return "Successfully deleted";
    }

    public String changeName(RegionChangeDTO dto) {
        Region region = repository.findById(dto.getId()).get();
        region.setName(dto.getName());
        repository.save(region);
        return "Region's name successfully changed";
    }
}
