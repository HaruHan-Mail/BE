package feedback.service;

import feedback.dto.PostFeedbackDto;
import feedback.entity.Feedback;

public interface FeedbackService {

    void saveFeedback(PostFeedbackDto postFeedbackDto);

}
