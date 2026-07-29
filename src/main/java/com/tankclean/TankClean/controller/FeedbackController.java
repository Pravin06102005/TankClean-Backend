package com.tankclean.TankClean.controller;

import com.tankclean.TankClean.dto.FeedbackRequest;
import com.tankclean.TankClean.entity.Feedback;
import com.tankclean.TankClean.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public Feedback createFeedback(@RequestBody FeedbackRequest feedbackRequest) {
        return feedbackService.addFeedback(feedbackRequest);
    }

    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackService.getFeedbackAll();
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable("id") Long id) {
        FeedbackRequest request = new FeedbackRequest();
        request.setBookingId(id);
        feedbackService.deleteFeedback(request);
    }

}
