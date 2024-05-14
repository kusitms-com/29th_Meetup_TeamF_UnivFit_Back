package backend.univfit.domain.apply.api.dto.response;

import java.util.List;

public record AnnouncementListResponse(
        List<AnnouncementResponse> announcementResponseList,
        Integer totalAnnouncement
) {
    public static AnnouncementListResponse of(List<AnnouncementResponse> announcementResponseList, Integer totalAnnouncement) {
        return new AnnouncementListResponse(announcementResponseList, totalAnnouncement);
    }
}
