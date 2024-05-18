package backend.univfit.domain.apply.api.dto.response;

public record AnnouncementBySearchResponse(
        Long announcementId,
        String scholarShipImage,
        String scholarShipName,
        String scholarshipFoundation,
        String applicationPeriod
) {
    public static AnnouncementBySearchResponse of(Long announcementId, String scholarShipImage, String scholarShipName, String scholarshipFoundation, String applicationPeriod) {
        return new AnnouncementBySearchResponse(announcementId, scholarShipImage, scholarShipName, scholarshipFoundation, applicationPeriod);
    }
}

