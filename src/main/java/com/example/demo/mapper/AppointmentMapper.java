package com.example.demo.mapper;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    Appointment toModel(AppointmentDto dto);

    AppointmentDto toDto(Appointment model);
}
