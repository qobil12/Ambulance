package com.company.mapper;

import com.company.dto.BrigadeDTO;
import com.company.entity.Brigade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class BrigadeMapper {
    public static final BrigadeMapper INSTANCE = Mappers.getMapper(BrigadeMapper.class);

    @Mapping(target = "carId", source = "car.id")
    @Mapping(target = "regionId", source = "region.id")
    @Mapping(target = "doctorId", source = "doctor.id")
    public abstract BrigadeDTO toBrigadeDTO(final Brigade brigade);

    @Mapping(target = "doctor.id", source = "doctorId")
    @Mapping(target = "region.id", source = "regionId")
    @Mapping(target = "car.id", source = "carId")
    public abstract Brigade toBrigadeEntity(final BrigadeDTO brigadeDTO);
}
