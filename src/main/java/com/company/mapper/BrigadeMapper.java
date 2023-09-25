package com.company.mapper;

import com.company.dto.BrigadeDTO;
import com.company.entity.Brigade;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class BrigadeMapper  {
    public static final BrigadeMapper INSTANCE = Mappers.getMapper(BrigadeMapper.class);


    public abstract BrigadeDTO toBrigadeDTO(final Brigade brigade);

    public abstract Brigade toBrigadeEntity(final BrigadeDTO brigadeDTO);
}
