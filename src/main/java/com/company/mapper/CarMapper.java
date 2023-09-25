package com.company.mapper;

import com.company.dto.CarDTO;
import com.company.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class CarMapper  {
    public static final CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    public abstract CarDTO toCarDTO(final Car car);

    public abstract Car toCarEntity(final CarDTO carDTO);
}