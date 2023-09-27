package com.company.mapper;

import com.company.dto.PatientApplicationDTO;
import com.company.entity.PatientApplication;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class PatientApplicationMapper {

    public static final PatientApplicationMapper INSTANCE= Mappers.getMapper(PatientApplicationMapper.class);
    public abstract PatientApplicationDTO toPatientApplicationDTO(final PatientApplication patientApplication);
    public  abstract PatientApplication toPatientApplicationEntity(final PatientApplicationDTO patientApplicationDTO);
}
