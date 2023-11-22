package com.example.demo.mapper;

import com.example.demo.dto.AddressDto;
import com.example.demo.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toModel(AddressDto dto);

    AddressDto toDto(Address model);
}
