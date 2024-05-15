package backend.univfit.domain.apply.api.dto.request;

import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.comment.entity.CommentEntity;
import backend.univfit.domain.member.entity.Member;

public record CommentRequest(
        String commentContents
) {
    public static CommentEntity toEntity(String commentContents, AnnouncementEntity announcementEntity, Member member) {
        return CommentEntity.of(
                null,
                commentContents,
                announcementEntity,
                member
        );
    }

}
