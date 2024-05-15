package backend.univfit.domain.apply.api.dto.response;

import java.util.List;

public record AnnouncementDetailResponse(
        Long scholarshipId,
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
    public static AnnouncementDetailResponse of(Long scholarshipId,String scholarShipName, String scholarShipFoundation,String remainingDay,
                                                String applyPossible,
                                                String supportAmount,
                                                String applicationPeriod, String hashTag, List<String> applyCondition,
                                                String detailContents) {
        return new AnnouncementDetailResponse(scholarshipId,
                scholarShipName, scholarShipFoundation, remainingDay, applyPossible,
                supportAmount, applicationPeriod, hashTag, applyCondition,
                detailContents
        );

    }
}
