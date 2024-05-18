package backend.univfit.domain.apply.api.dto.response;


public record AnnouncementResponse(
        Long scholarshipId,
        String scholarShipImage,
        String scholarshipName,
        String scholarshipFoundation,
        String scholarshipStatus,
        String applicationPeriod,
        String remainingDays,
        String applyPossible
) {
    public static AnnouncementResponse of(Long scholarshipId, String scholarShipImage, String scholarshipName, String scholarshipFoundation, String scholarshipStatus,
                                          String applicationPeriod, String remainingDays, String applyPossible) {

        return new AnnouncementResponse(scholarshipId, scholarShipImage,
                scholarshipName,
                scholarshipFoundation,
                scholarshipStatus,
                applicationPeriod,
                remainingDays,
                applyPossible);
    }
}
