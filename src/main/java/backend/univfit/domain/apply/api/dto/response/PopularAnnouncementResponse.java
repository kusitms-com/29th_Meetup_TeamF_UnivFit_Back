package backend.univfit.domain.apply.api.dto.response;

public record PopularAnnouncementResponse(
        Long announcementId,
        String scholarShipImage,
        String scholarShipName,
        String scholarshipFoundation,
        String applicationPeriod,
        Integer howManyLikes
) {
    public static PopularAnnouncementResponse of(Long announcementId, String scholarShipImage, String scholarShipName, String scholarshipFoundation,
                                                 String applicationPeriod, Integer howManyLikes) {
        return new PopularAnnouncementResponse(announcementId, scholarShipImage, scholarShipName, scholarshipFoundation,
                applicationPeriod, howManyLikes);
    }
}
