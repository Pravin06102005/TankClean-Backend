package com.tankclean.TankClean.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "worker")
@Setter
@Getter
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workerId;
    private String name;
    private String phone;
    private String status;

    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "worker")
    @JsonIgnore
    private List<Booking> bookings;
}
