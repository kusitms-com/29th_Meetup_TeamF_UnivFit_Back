package backend.univfit.domain.apply.application.api.dto.response;

public record AnnouncementDetailResponse(
        String scholarshipName,
        String scholarshipFoundation,
        Integer supportAmount,
        String applicationPeriod,
        String hashTag,
        String applicationConditions,
        String detailContents
) {
    public static AnnouncementDetailResponse of(String scholarShipName, String scholarShipFoundation, Integer supportAmount,
                                                String applicationPeriod, String hashTag, String applicationConditions,
                                                String detailContents) {
        return new AnnouncementDetailResponse(
                scholarShipName, scholarShipFoundation,
                supportAmount, applicationPeriod, hashTag, applicationConditions,
                detailContents
        );

    }
}
