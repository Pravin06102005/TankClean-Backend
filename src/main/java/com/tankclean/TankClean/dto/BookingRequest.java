package com.tankclean.TankClean.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingRequest {

//    @NotNull
    private Long addressId;

    @NotNull
    private Long serviceId;

    @NotNull
    private LocalDate serviceDate;
}
