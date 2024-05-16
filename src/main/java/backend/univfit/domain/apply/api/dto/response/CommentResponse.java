package backend.univfit.domain.apply.api.dto.response;

public record CommentResponse(
        Long memberId,
        String nickname,
        String comment,
        String hours
) {
    public static CommentResponse of(Long memberId, String nickname, String comment, String hours) {
        return new CommentResponse(memberId, nickname, comment, hours);
    }
}
