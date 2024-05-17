package backend.univfit.domain.apply.api.dto.response;

import java.util.List;

public record AnnouncementListBySearchResponse(
        List<AnnouncementBySearchResponse> announcementBySearchResponseList
) {
    public static AnnouncementListBySearchResponse of(List<AnnouncementBySearchResponse> announcementBySearchResponseList) {
        return new AnnouncementListBySearchResponse(announcementBySearchResponseList);
    }
}
