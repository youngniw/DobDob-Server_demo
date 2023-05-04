package com.tave7.dobdob.mapper;

import com.tave7.dobdob.dto.UserDto;
import com.tave7.dobdob.entity.Users;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, Users> {
    @Mapping(source = "id", target = "userId")
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "socialType", ignore = true)
    @Mapping(target = "location.locationId", ignore = true)
    @Mapping(target = "location.si", ignore = true)
    @Mapping(target = "location.gu", ignore = true)
    Users toEntity(UserDto userDto);

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "location", target = "Location")
    UserDto toDto(Users user);

    @Named("partialUpdate")
    @Mapping(target = "userId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Users entity, UserDto dto);

    default Users fromIdx(Long idx) {
        if (idx == null) {
            return null;
        }
        return Users.builder()
                .userId(idx)
                .build();
    }
}
