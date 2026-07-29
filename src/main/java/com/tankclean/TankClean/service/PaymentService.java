package com.tankclean.TankClean.service;

import com.tankclean.TankClean.dto.PaymentRequest;
import com.tankclean.TankClean.entity.Booking;
import com.tankclean.TankClean.entity.Payment;
import com.tankclean.TankClean.entity.Users;
import com.tankclean.TankClean.repository.BookingRepository;
import com.tankclean.TankClean.repository.PaymentRepository;
import com.tankclean.TankClean.repository.UserRepository;
import com.tankclean.TankClean.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public Payment makePayment(PaymentRequest paymentRequest){

        String email = SecurityUtil.getCurrentUserEmail();
        Users user = userRepository.findByEmail(email).orElseThrow();

        Booking  booking = bookingRepository.findById(paymentRequest.getBookingId()).orElseThrow();

        if (!booking.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("You cannot pay for another user's booking");
        }

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setPaymentStatus("PAID");
        payment.setPaymentDate(LocalDate.now());
        return paymentRepository.save(payment);

    }
}
