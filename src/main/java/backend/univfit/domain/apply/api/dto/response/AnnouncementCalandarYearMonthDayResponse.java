package backend.univfit.domain.apply.api.dto.response;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnnouncementCalandarYearMonthDayResponse {
    private ArrayList<AnnouncementCalandarInfo> announcementCalandarDayList;

    public static AnnouncementCalandarYearMonthDayResponse of(
            ArrayList<AnnouncementCalandarInfo> announcementCalandarDayList
    ){
        return AnnouncementCalandarYearMonthDayResponse.builder()
                .announcementCalandarDayList(announcementCalandarDayList)
                .build();
    }
}
