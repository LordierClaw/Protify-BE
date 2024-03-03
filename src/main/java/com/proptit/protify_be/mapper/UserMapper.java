package com.proptit.protify_be.mapper;

import com.proptit.protify_be.dto.UserBasicInfoDto;
import com.proptit.protify_be.dto.UserRegisterDto;
import com.proptit.protify_be.dto.UserUpdateInfoDto;
import com.proptit.protify_be.dto.UserUpdatePasswordDto;
import com.proptit.protify_be.entity.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserEntity toEntity(UserBasicInfoDto userBasicInfoDto);
    UserEntity toEntity(UserRegisterDto userRegisterDto);
    UserEntity toEntity(UserUpdateInfoDto userUpdateInfoDto);
    UserEntity toEntity(UserUpdatePasswordDto userUpdatePasswordDto);

    UserBasicInfoDto toUserBasicInfoDto(UserEntity userEntity);
    UserRegisterDto toUserRegisterDto(UserEntity userEntity);
    UserUpdateInfoDto toUserUpdateInfoDto(UserEntity userEntity);
    UserUpdatePasswordDto toUserUpdatePasswordDto(UserEntity userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserBasicInfoDto userBasicInfoDto, @MappingTarget UserEntity userEntity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserRegisterDto userRegisterDto, @MappingTarget UserEntity userEntity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserUpdateInfoDto userUpdateInfoDto, @MappingTarget UserEntity userEntity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserUpdatePasswordDto userUpdatePasswordDto, @MappingTarget UserEntity userEntity);
}