package com.company.mapper;

import com.company.dto.RegionDTO;
import com.company.entity.Region;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class RegionMapper extends InstantMapper {
    public static final RegionMapper INSTANCE = Mappers.getMapper(RegionMapper.class);

    public abstract RegionDTO toRegionDTO(final Region region);
}
