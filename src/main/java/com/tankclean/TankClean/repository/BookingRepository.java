package com.tankclean.TankClean.repository;

import com.tankclean.TankClean.entity.Booking;
import jakarta.validation.constraints.Digits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserUserId(Long userid);
    List<Booking> findByStatus(String status);
}
