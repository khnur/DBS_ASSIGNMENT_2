package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressDto {
    @JsonProperty("address_id")
    private Long addressId;
    @JsonProperty("house_number")
    private String houseNumber;
    private String street;
    private String town;
    private MemberDto member;
}
