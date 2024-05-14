package backend.univfit.domain.apply.application.service;

import backend.univfit.domain.apply.application.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.application.api.dto.response.AnnouncementResponse;
import backend.univfit.domain.apply.repository.AnnouncementJpaRepository;

import java.util.List;

public interface AnnouncementService {
    AnnouncementListResponse getAnnouncementList(List<String> statuses);

    AnnouncementResponse getAnnouncement(Long announcementId);


}
