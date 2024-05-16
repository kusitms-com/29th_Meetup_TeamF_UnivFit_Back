package backend.univfit.domain.apply.api.dto.response;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnnouncementCalandarInfo {
    private Long id;
    private LocalDate endDocumentDate;
    private String announcementImageUrl;
    private String scholarShipName;
    private String scholarShipFoundation;
    private String applicationPeriod;

    public static AnnouncementCalandarInfo of(
            Long id,
            LocalDate endDocumentDate,
            String announcementImageUrl,
            String scholarShipName,
            String scholarShipFoundation,
            String applicationPeriod
    ){
        return AnnouncementCalandarInfo.builder()
                .id(id)
                .endDocumentDate(endDocumentDate)
                .announcementImageUrl(announcementImageUrl)
                .scholarShipName(scholarShipName)
                .scholarShipFoundation(scholarShipFoundation)
                .applicationPeriod(applicationPeriod)
                .build();
    }
}
