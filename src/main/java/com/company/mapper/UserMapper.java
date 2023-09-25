package com.company.mapper;

import com.company.dto.UserDTO;
import com.company.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    public abstract UserDTO toUserDto(final UserEntity user);

    public abstract UserEntity toUserEntity(final UserDTO userDTO);
    //public abstract UserEntity toUserEntity(final ChangeUserInfoDTO userInfoDTO);
}
