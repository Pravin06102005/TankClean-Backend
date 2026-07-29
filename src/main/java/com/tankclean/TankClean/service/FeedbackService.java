package com.tankclean.TankClean.service;

import com.tankclean.TankClean.dto.FeedbackRequest;
import com.tankclean.TankClean.entity.Booking;
import com.tankclean.TankClean.entity.Feedback;
import com.tankclean.TankClean.entity.Users;
import com.tankclean.TankClean.repository.BookingRepository;
import com.tankclean.TankClean.repository.FeedbackRepository;
import com.tankclean.TankClean.repository.UserRepository;
import com.tankclean.TankClean.security.SecurityUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private BookingRepository  bookingRepository;

    @Autowired
    private UserRepository userRepository;

    public Feedback addFeedback(FeedbackRequest feedbackRequest) {
        String email = SecurityUtil.getCurrentUserEmail();
        Users user = userRepository.findByEmail(email).orElseThrow();
        Booking booking = bookingRepository.findById(feedbackRequest.getBookingId()).orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("You cannot give feedback for another user's booking");
        }

        Feedback feedback = new Feedback();
        feedback.setBooking(booking);
        feedback.setRating(feedbackRequest.getRating());
        feedback.setComment(feedbackRequest.getComment());

        return  feedbackRepository.save(feedback);
    }
    public List<Feedback> getFeedbackAll() {
        return feedbackRepository.findAll();
    }

    @Transactional
    public void deleteFeedback(FeedbackRequest feedbackRequest) {
        Long bId = feedbackRequest.getBookingId();
        feedbackRepository.deleteByBookingId(bId);

        // 2. Force Hibernate to send the command to the DB right now
        feedbackRepository.flush();

    }
}
