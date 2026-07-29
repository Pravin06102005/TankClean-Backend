package com.tankclean.TankClean.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Ye import zaroori hai
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Setter
@Getter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private LocalDate bookingDate;
    private LocalDate serviceDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"bookings", "password", "addresses"}) // Loop todne ke liye
    private Users user;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonIgnoreProperties({"user"}) // Address ke andar wapis user load mat karo
    private Address address;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity services;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = true)
    @JsonIgnoreProperties({"bookings"}) // Worker ke andar wapis bookings load mat karo
    private Worker worker;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("booking")
    private Payment payment;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("booking")
    private Feedback feedback;
}