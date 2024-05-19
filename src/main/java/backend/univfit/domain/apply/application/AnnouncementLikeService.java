package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;

public interface AnnouncementLikeService {
    void likeAnnouncement(Long announcementId, MemberInfoObject memberInfoObject);
    void deleteLikeAnnouncement(Long announcementId, MemberInfoObject memberInfoObject);

    AnnouncementListResponse getLikeAnnouncementList(MemberInfoObject memberInfoObject);
}
