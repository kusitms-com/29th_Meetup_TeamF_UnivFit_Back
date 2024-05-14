package backend.univfit.global.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GeneralResponse {
    private String data;

    public static GeneralResponse of(

    ){
        return GeneralResponse.builder()
                .data("요청에 성공하셨습니다.")
                .build();
    }
}
