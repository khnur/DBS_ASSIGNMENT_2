package com.example.demo.mapper;

import com.example.demo.dto.MemberDto;
import com.example.demo.model.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    Member toModel(MemberDto dto);

    MemberDto toDto(Member model);
}
