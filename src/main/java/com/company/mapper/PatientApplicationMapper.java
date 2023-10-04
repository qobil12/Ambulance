package com.company.mapper;

import com.company.dto.PatientApplicationDTO;
import com.company.entity.PatientApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class PatientApplicationMapper {

    public static final PatientApplicationMapper INSTANCE = Mappers.getMapper(PatientApplicationMapper.class);

    @Mapping(target = "address", source = "fullAddress")
    public abstract PatientApplicationDTO toPatientApplicationDTO(final PatientApplication patientApplication);

    @Mapping(target = "fullAddress", source = "address")
    public abstract PatientApplication toPatientApplicationEntity(final PatientApplicationDTO patientApplicationDTO);
}
