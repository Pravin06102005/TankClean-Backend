package com.tankclean.TankClean.repository;

import com.tankclean.TankClean.entity.Feedback;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("DELETE FROM Feedback f WHERE f.booking.bookingId = :bookingId")
    void deleteByBookingId(@Param("bookingId") Long bookingId);
}
