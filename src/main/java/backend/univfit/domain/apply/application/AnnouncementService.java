package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.AnnouncementDetailResponse;
import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.api.dto.response.AnnouncementResponse;
import backend.univfit.domain.apply.api.dto.response.ScholarShipFoundationResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;

import java.util.List;

public interface AnnouncementService {
    AnnouncementListResponse getAnnouncementList(List<String> statuses);

    AnnouncementDetailResponse getAnnouncement(Long announcementId/**, MemberInfoObject memberInfoObject**/);

    ScholarShipFoundationResponse getScholarShipFoundationContents(Long announcementId);


}
