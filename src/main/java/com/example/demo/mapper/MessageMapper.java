package com.example.demo.mapper;

import com.example.demo.dto.MessageDto;
import com.example.demo.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    Message toModel(MessageDto dto);

    MessageDto toDto(Message model);
}
