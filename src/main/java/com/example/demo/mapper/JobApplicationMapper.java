package com.example.demo.mapper;

import com.example.demo.dto.JobApplicationDto;
import com.example.demo.model.JobApplication;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobApplicationMapper {
    JobApplicationMapper INSTANCE = Mappers.getMapper(JobApplicationMapper.class);

    JobApplication toModel(JobApplicationDto dto);

    JobApplicationDto toDto(JobApplication model);
}
