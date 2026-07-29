package com.tankclean.TankClean.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

    @NotNull
    private Long bookingId;

    @NotNull
    private Double amount;

    private String paymentMethod;
}
