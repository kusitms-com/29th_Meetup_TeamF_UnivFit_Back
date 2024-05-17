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
    private Long id;
    private LocalDate endDocumentDate;
    private String announcementImageUrl;
    private String scholarShipName;
    private String scholarShipFoundation;
    private String applicationPeriod;
    private String applyStatus;
    public static ApplyListEntry of(
            Long id,
            LocalDate endDocumentDate,
            String announcementImageUrl,
            String scholarShipName,
            String scholarShipFoundation,
            String applicationPeriod,
            String applyStatus
    ){
        return ApplyListEntry.builder()
                .id(id)
                .endDocumentDate(endDocumentDate)
                .announcementImageUrl(announcementImageUrl)
                .scholarShipName(scholarShipName)
                .scholarShipFoundation(scholarShipFoundation)
                .applicationPeriod(applicationPeriod)
                .applyStatus(applyStatus)
                .build();
    }
}
