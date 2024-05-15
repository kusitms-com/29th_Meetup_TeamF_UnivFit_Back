package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.request.CommentRequest;
import backend.univfit.domain.apply.api.dto.response.CommentResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AnnouncementCommentService {
    void createAnnouncementComments(CommentRequest commentRequest, Long announcementId);

    List<CommentResponse> getAnnouncementComments(Long announcementId);
}
