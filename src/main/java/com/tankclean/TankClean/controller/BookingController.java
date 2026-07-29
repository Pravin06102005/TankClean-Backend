package com.tankclean.TankClean.controller;

import com.tankclean.TankClean.dto.BookingRequest;
import com.tankclean.TankClean.dto.BookingStatusUpdateRequest;
import com.tankclean.TankClean.entity.Booking;
import com.tankclean.TankClean.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @PostMapping
    public Booking createBooking(@Valid @RequestBody BookingRequest  bookingRequest){
        return bookingService.createBooking(bookingRequest);
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<Booking> updateBookingByAdmin(
            @PathVariable Long id,
            @RequestBody Map<String, Object> payload) {

        String status = (String) payload.get("status");

        // workerId ko safely handle karein (Integer ya Long ho sakta hai)
        Object wIdObj = payload.get("workerId");
        Long workerId = null;
        if (wIdObj != null && !wIdObj.toString().isEmpty()) {
            workerId = Long.parseLong(wIdObj.toString());
        }

        Booking updated = bookingService.updateBookingAdmin(id, status, workerId);
        return ResponseEntity.ok(updated);
    }


    @GetMapping("/my")
    public List<Booking> getBookingByID(){
        return bookingService.getMyBookings();
    }

    @GetMapping
    public List<Booking>getAllBooking(){
        return bookingService.getAllBookings();
    }


    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
    }

}
