package backend.univfit.domain.apply.api.dto.response;

import backend.univfit.domain.apply.entity.enums.ApplyStatus;
import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplyListDetailResponse {
    private Long applyId;
    private Long announcementId;
    private String applyStatus;
    private String announcementImageUrl;
    private String scholarShipName;
    private String scholarShipFoundation;
    private String applicationPeriod;
    private ArrayList<MyCoverLetterListEntry> myCoverLetterList;


    public static ApplyListDetailResponse of(
            Long applyId,
            Long announcementId,
            String applyStatus,
            String announcementImageUrl,
            String scholarShipName,
            String scholarShipFoundation,
            String applicationPeriod,
            ArrayList<MyCoverLetterListEntry> myCoverLetterList
    ){
        return ApplyListDetailResponse.builder()
                .applyId(applyId)
                .announcementId(announcementId)
                .applyStatus(applyStatus)
                .announcementImageUrl(announcementImageUrl)
                .scholarShipName(scholarShipName)
                .scholarShipFoundation(scholarShipFoundation)
                .applicationPeriod(applicationPeriod)
                .myCoverLetterList(myCoverLetterList)
                .build();
    }

}
