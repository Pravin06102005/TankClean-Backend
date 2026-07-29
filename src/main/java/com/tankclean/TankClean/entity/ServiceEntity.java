package com.tankclean.TankClean.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "service")
@Getter
@Setter
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    private String serviceName;
    private String description;
    private Double price;

    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "services")
    @JsonIgnore
    private List<Booking> booking;
}
