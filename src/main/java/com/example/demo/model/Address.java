package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "house_number", length = 50)
    private String houseNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "town", length = 50)
    private String town;

    @ManyToOne
    @JoinColumn(
            name = "member_user_id",
            referencedColumnName = "member_user_id",
            foreignKey = @ForeignKey(name = "member_address_fk"),
            nullable = false
    )
    private Member member;
}
