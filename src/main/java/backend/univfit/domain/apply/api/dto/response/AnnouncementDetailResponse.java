package backend.univfit.domain.apply.api.dto.response;

import java.util.List;

public record AnnouncementDetailResponse(
        String scholarshipName,
        String scholarshipFoundation,
        String remainingDay,
        String applyPossible,
        String supportAmount,
        String applicationPeriod,
        String hashTag,
        List<String> applyCondition,
//        String applicationConditions,
        String detailContents
) {
    public static AnnouncementDetailResponse of(String scholarShipName, String scholarShipFoundation,String remainingDay,
                                                String applyPossible,
                                                String supportAmount,
                                                String applicationPeriod, String hashTag, List<String> applyCondition,
                                                String detailContents) {
        return new AnnouncementDetailResponse(
                scholarShipName, scholarShipFoundation, remainingDay, applyPossible,
                supportAmount, applicationPeriod, hashTag, applyCondition,
                detailContents
        );

    }
}
