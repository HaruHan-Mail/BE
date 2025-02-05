package feedback.service;

import feedback.dto.PostFeedbackDto;
import feedback.entity.Feedback;
import feedback.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService{

    private final FeedbackRepository feedbackRepository;

    @Override
    public void saveFeedback(PostFeedbackDto postFeedbackDto) {
        Feedback feedback = new Feedback(postFeedbackDto.feedback_content());
        feedbackRepository.save(feedback);
    }
}
