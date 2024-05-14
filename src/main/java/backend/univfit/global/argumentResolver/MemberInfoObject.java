package backend.univfit.global.argumentResolver;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoObject {
    private String socialLoginInfo;
    private Long socialPK;
    private Long memberId;

    public static MemberInfoObject of(
            String socialLoginInfo,
            Long socialPK,
            Long memberId
    ){
        return MemberInfoObject.builder()
                .socialLoginInfo(socialLoginInfo)
                .socialPK(socialPK)
                .memberId(memberId)
                .build();
    }
}
