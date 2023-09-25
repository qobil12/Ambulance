package com.company.mapper;

import com.company.dto.DoctorDTO;
import com.company.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class DoctorMapper  {
    public static final DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    public abstract DoctorDTO toDoctorDTO(final Doctor doctor);

    public abstract Doctor toDoctorEntity(final DoctorDTO dto);
}
