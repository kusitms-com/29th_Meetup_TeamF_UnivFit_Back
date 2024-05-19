package backend.univfit.domain.apply.api.dto.response;

public record PopularAnnouncementResponse(
        Long announcementId,
        String scholarShipImage,
        String scholarShipName,
        String scholarshipFoundation,
        String applicationPeriod,
        Long howManyLikes
) {
    public static PopularAnnouncementResponse of(Long announcementId, String scholarShipImage, String scholarShipName, String scholarshipFoundation,
                                                 String applicationPeriod, Long howManyLikes) {
        return new PopularAnnouncementResponse(announcementId, scholarShipImage, scholarShipName, scholarshipFoundation,
                applicationPeriod, howManyLikes);
    }
}
