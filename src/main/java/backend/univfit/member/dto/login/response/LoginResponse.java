package backend.univfit.member.dto.login.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginResponse {
    private Boolean isMember;
    private Long memberId;
    private String accessToken;
    private String refreshToken;

    public static LoginResponse of(
            Boolean isMember,
            Long memberId,
            String accessToken,
            String refreshToken
    ){
        return LoginResponse.builder()
                .isMember(isMember)
                .memberId(memberId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
 }
