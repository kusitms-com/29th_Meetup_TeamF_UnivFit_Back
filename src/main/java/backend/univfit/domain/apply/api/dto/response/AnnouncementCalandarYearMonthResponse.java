package backend.univfit.domain.apply.api.dto.response;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnnouncementCalandarYearMonthResponse {
    ArrayList<Integer> dayList;

    public static AnnouncementCalandarYearMonthResponse of(
            ArrayList<Integer> dayList
    ){
        return AnnouncementCalandarYearMonthResponse.builder()
                .dayList(dayList)
                .build();
    }
}
