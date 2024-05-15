package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.request.CommentRequest;
import org.springframework.web.bind.annotation.PathVariable;

public interface AnnouncementCommentService {
    void createAnnouncementComments(CommentRequest commentRequest, Long announcementId);
}
