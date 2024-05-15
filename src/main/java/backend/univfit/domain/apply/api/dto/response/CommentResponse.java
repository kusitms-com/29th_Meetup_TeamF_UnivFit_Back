package backend.univfit.domain.apply.api.dto.response;

public record CommentResponse(
        Long memberId,
        String nickname,
        String comment
) {
    public static CommentResponse of(Long memberId, String nickname, String comment) {
        return new CommentResponse(memberId, nickname, comment);
    }
}
