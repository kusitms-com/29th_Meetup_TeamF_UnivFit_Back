package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;

import java.util.List;

public interface FitAnnouncementService {
    AnnouncementListResponse getAnnouncementList(String statuses);
}
