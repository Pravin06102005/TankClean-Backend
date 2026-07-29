package com.tankclean.TankClean.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "address")
@Setter
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;
    private String city;
    private String area;
    private String building;
    private String pincode;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private Users user;
}
