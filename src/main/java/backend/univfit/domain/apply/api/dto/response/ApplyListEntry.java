package backend.univfit.domain.apply.api.dto.response;

import backend.univfit.domain.apply.entity.enums.ApplyStatus;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplyListEntry {
    private Long applyId;
    private LocalDate endDocumentDate;
    private String announcementImageUrl;
    private String scholarShipName;
    private String scholarShipFoundation;
    private String applicationPeriod;
    private String applyStatus;
    public static ApplyListEntry of(
            Long applyId,
            LocalDate endDocumentDate,
            String announcementImageUrl,
            String scholarShipName,
            String scholarShipFoundation,
            String applicationPeriod,
            String applyStatus
    ){
        return ApplyListEntry.builder()
                .applyId(applyId)
                .endDocumentDate(endDocumentDate)
                .announcementImageUrl(announcementImageUrl)
                .scholarShipName(scholarShipName)
                .scholarShipFoundation(scholarShipFoundation)
                .applicationPeriod(applicationPeriod)
                .applyStatus(applyStatus)
                .build();
    }
}
