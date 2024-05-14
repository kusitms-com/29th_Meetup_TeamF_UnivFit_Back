package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.api.dto.response.AnnouncementResponse;

import java.util.List;

public interface AnnouncementService {
    AnnouncementListResponse getAnnouncementList(List<String> statuses);

    AnnouncementResponse getAnnouncement(Long announcementId);


}
