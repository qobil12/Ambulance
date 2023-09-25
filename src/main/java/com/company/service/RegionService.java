package com.company.service;

import com.company.dto.RegionDTO;
import com.company.entity.Region;
import com.company.exceptions.ItemNotFoundException;
import com.company.mapper.RegionMapper;
import com.company.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegionService {

    final
    RegionMapper regionMapper=RegionMapper.INSTANCE;
    final
    RegionRepository repository;

    @Autowired
    public RegionService( RegionRepository repository) {
        this.repository = repository;
    }

    public RegionDTO createRegion(RegionDTO dto) {
        Region region = new Region();
        region.setName(dto.getName().toUpperCase());
        return regionMapper.toRegionDTO(repository.save(region));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public RegionDTO changeName(RegionDTO dto) {
        Region region = repository.findById(dto.getId()).orElseThrow(() -> new ItemNotFoundException("Region doesn't exist with this ID"));

        region.setName(dto.getName());
        var saveRegion=repository.save(region);
        return regionMapper.toRegionDTO(saveRegion);

    }
}
