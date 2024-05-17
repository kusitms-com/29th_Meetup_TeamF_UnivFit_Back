package backend.univfit.domain.member.dto.response;

public record MyPageResponse(
        String nickname,
        String socialLoginName
) {
    public static MyPageResponse of(String nickname, String socialLoginName) {
        return new MyPageResponse(nickname, socialLoginName);
    }
}
