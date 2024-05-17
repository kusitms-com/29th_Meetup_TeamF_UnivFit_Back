package backend.univfit.domain.apply.api.dto.response;

import java.util.List;

public record PopularAnnouncementListResponse(
        List<PopularAnnouncementResponse> popularAnnouncementResponses
) {
    public static PopularAnnouncementListResponse of(List<PopularAnnouncementResponse> popularAnnouncementResponses) {
        return new PopularAnnouncementListResponse(popularAnnouncementResponses);
    }
}
