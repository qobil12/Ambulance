package com.company.mapper;

import com.company.dto.DoctorDTO;
import com.company.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class DoctorMapper {
    public static final DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Mapping(target = "regionId", source = "region.id")
    public abstract DoctorDTO toDoctorDTO(final Doctor doctor);

    @Mapping(target = "region.id", source = "regionId")
    public abstract Doctor toDoctorEntity(final DoctorDTO dto);
}
