package backend.univfit.domain.apply.api.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnnouncementCalandarInfo {
    private String announcementImageUrl;
    private String scholarShipName;
    private String scholarShipFoundation;
    private String applicationPeriod;

    public static AnnouncementCalandarInfo of(
            String announcementImageUrl,
            String scholarShipName,
            String scholarShipFoundation,
            String applicationPeriod
    ){
        return AnnouncementCalandarInfo.builder()
                .announcementImageUrl(announcementImageUrl)
                .scholarShipName(scholarShipName)
                .scholarShipFoundation(scholarShipFoundation)
                .applicationPeriod(applicationPeriod)
                .build();
    }
}
