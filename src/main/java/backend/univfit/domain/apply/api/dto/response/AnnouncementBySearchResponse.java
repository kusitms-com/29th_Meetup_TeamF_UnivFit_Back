package backend.univfit.domain.apply.api.dto.response;

public record AnnouncementBySearchResponse(
        Long announcementId,
        String scholarShipName,
        String scholarshipFoundation,
        String applicationPeriod
) {
    public static AnnouncementBySearchResponse of(Long announcementId, String scholarShipName, String scholarshipFoundation, String applicationPeriod) {
        return new AnnouncementBySearchResponse(announcementId, scholarShipName, scholarshipFoundation, applicationPeriod);
    }
}

