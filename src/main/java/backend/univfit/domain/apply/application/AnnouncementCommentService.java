package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.request.CommentRequest;
import backend.univfit.domain.apply.api.dto.response.CommentResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AnnouncementCommentService {
    void createAnnouncementComments(CommentRequest commentRequest, Long announcementId, MemberInfoObject memberInfoObject);

    List<CommentResponse> getAnnouncementComments(Long announcementId);
}
