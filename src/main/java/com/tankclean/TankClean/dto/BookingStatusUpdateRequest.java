package com.tankclean.TankClean.dto;

import com.tankclean.TankClean.entity.BookingStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingStatusUpdateRequest {

    private BookingStatus status;
}
