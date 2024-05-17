package backend.univfit.domain.apply.api.dto.response;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplyListResponse {
    private ArrayList<ApplyListEntry> applyList;

    public static ApplyListResponse of(
            ArrayList<ApplyListEntry> applyList
    ){
        return ApplyListResponse.builder()
                .applyList(applyList)
                .build();
    }
}
