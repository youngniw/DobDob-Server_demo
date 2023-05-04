package com.tave7.dobdob.mapper;

import com.tave7.dobdob.dto.LocationDto;
import com.tave7.dobdob.entity.Locations;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LocationMapper extends EntityMapper<LocationDto, Locations> {
    @Mapping(target = "locationId", ignore = true)
    @Mapping(target = "si", ignore = true)
    @Mapping(target = "gu", ignore = true)
    Locations toEntity(LocationDto locationDto);

    LocationDto toDto(Locations location);

    @Named("partialUpdate")
    @Mapping(target = "locationId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Locations entity, LocationDto dto);

    default Locations fromIdx(Long idx) {
        if (idx == null) {
            return null;
        }
        return Locations.builder()
                .locationId(idx)
                .build();
    }
}
