package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.request.CommentRequest;
import backend.univfit.domain.apply.api.dto.response.AnnouncementDetailResponse;
import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.api.dto.response.ScholarShipFoundationResponse;
import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.exception.AnnouncementException;
import backend.univfit.domain.apply.repository.AnnouncementJpaRepository;
import backend.univfit.domain.apply.repository.CommentJpaRepository;
import backend.univfit.domain.comment.entity.CommentEntity;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.exception.MemberException;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static backend.univfit.global.error.status.ErrorStatus.ANNOUNCEMENT_NOT_FOUND;
import static backend.univfit.global.error.status.ErrorStatus.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementCommentServiceImpl implements AnnouncementCommentService {
    private final CommentJpaRepository commentJpaRepository;
    private final AnnouncementJpaRepository announcementJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void createAnnouncementComments(CommentRequest commentRequest, Long announcementId) {
        Long memberId = 1L;
        Member member = memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        AnnouncementEntity ae = announcementJpaRepository.findById(announcementId)
                .orElseThrow(() -> new AnnouncementException(ANNOUNCEMENT_NOT_FOUND));

        CommentEntity comment = CommentRequest.toEntity(commentRequest.commentContents(), ae, member);
        commentJpaRepository.save(comment);
    }
}
