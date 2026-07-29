package com.tankclean.TankClean.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
@Setter
@Getter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Double amount;
    private LocalDate paymentDate;
    private String paymentStatus;
    private String paymentMethod;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
