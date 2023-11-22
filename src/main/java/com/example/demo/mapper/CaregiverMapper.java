package com.example.demo.mapper;

import com.example.demo.dto.CaregiverDto;
import com.example.demo.model.Caregiver;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CaregiverMapper {
    CaregiverMapper INSTANCE = Mappers.getMapper(CaregiverMapper.class);

    Caregiver toModel(CaregiverDto dto);

    CaregiverDto toDto(Caregiver model);
}
