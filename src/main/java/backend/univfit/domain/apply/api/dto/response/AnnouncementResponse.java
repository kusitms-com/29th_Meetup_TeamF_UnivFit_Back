package backend.univfit.domain.apply.api.dto.response;


public record AnnouncementResponse(
        String scholarshipName,
        String scholarshipFoundation,
        String scholarshipStatus,
        String applicationPeriod,
        String remainingDays,
        String applyPossible
) {
    public static AnnouncementResponse of(String scholarshipName, String scholarshipFoundation, String scholarshipStatus,
                                          String applicationPeriod, String remainingDays, String applyPossible) {

        return new AnnouncementResponse(scholarshipName,
                scholarshipFoundation,
                scholarshipStatus,
                applicationPeriod,
                remainingDays,
                applyPossible);
    }
}
