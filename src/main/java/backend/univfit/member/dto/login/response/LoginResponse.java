package backend.univfit.member.dto.login.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginResponse {
    private Long memberId;
    private Boolean isOnboarding;
    private String accessToken;
    private String refreshToken;

    public static LoginResponse of(
            Long memberId,
            Boolean isOnboarding,
            String accessToken,
            String refreshToken
    ){
        return LoginResponse.builder()
                .isOnboarding(isOnboarding)
                .memberId(memberId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
 }
