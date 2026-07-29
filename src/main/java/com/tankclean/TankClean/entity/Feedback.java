package com.tankclean.TankClean.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "feedback")
@Setter
@Getter
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    private Integer rating;
    private String comment;

    @OneToOne
    @JoinColumn(name = "booking_id")
    @JsonIgnoreProperties("feedback")
    private Booking booking;
}
