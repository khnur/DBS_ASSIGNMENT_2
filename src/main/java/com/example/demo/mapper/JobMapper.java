package com.example.demo.mapper;

import com.example.demo.dto.JobDto;
import com.example.demo.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    Job toModel(JobDto dto);

    JobDto toDto(Job model);
}
