package backend.univfit.domain.apply.api.dto.response;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnnouncementCalandarYearMonthResponse {
    ArrayList<Integer> lastDayList;
    ArrayList<Integer> dayList;


    public static AnnouncementCalandarYearMonthResponse of(
            ArrayList<Integer> lastDayList,
            ArrayList<Integer> dayList
    ){
        return AnnouncementCalandarYearMonthResponse.builder()
                .lastDayList(lastDayList)
                .dayList(dayList)
                .build();
    }
}
