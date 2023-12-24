package WCheck.services;

import WCheck.entities.Feedback;
import WCheck.repos.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback getFeedback(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
