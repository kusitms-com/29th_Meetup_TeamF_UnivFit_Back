package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;

import java.util.List;

public interface FitAnnouncementService {
    AnnouncementListResponse getAnnouncementList(String statuses, MemberInfoObject memberInfoObject);

}
