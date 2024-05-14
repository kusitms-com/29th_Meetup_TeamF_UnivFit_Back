package backend.univfit.domain.member.dto.response;

import backend.univfit.global.Interceptor.AccessTokenInterceptor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccessTokenResponse {
    private String accessToken;

    public static AccessTokenResponse of(
            String accessToken
    ){
        return AccessTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
