package com.tankclean.TankClean.controller;

import com.tankclean.TankClean.dto.PaymentRequest;
import com.tankclean.TankClean.entity.Booking;
import com.tankclean.TankClean.entity.Payment;
import com.tankclean.TankClean.service.BookingService;
import com.tankclean.TankClean.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Payment makePayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        return paymentService.makePayment(paymentRequest);
    }

    @GetMapping("/my")
    public List<Booking> getMyBookings() {
        return bookingService.getMyBookings();
    }

}
