package com.company.mapper;

import com.company.dto.ApplicationDTO;
import com.company.dto.ApplicationInfoDTO;
import com.company.entity.Application;
import com.company.entity.PatientApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class ApplicationMapper {

    public static final ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    @Mapping(target = "region.id", source = "patientApplication.regionId")
    @Mapping(target = "brigade.id", source = "dto.brigadeId")
    @Mapping(target = "user.id", source = "patientApplication.userId")
    public abstract Application toEntity(ApplicationDTO dto, PatientApplication patientApplication);

    @Mapping(target = "patientFullName", expression = "java(application.getUser().getName() + \" \" + application.getUser().getSurname())")
    @Mapping(target = "brigadeId", source = "brigade.id")
    @Mapping(target = "regionId", source = "region.id")
    public abstract ApplicationInfoDTO toApplicationInfoDTO(final Application application);

}
