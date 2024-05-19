package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.request.CommentRequest;
import backend.univfit.domain.apply.api.dto.response.CommentResponse;
import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.exception.AnnouncementException;
import backend.univfit.domain.apply.repository.AnnouncementJpaRepository;
import backend.univfit.domain.apply.repository.CommentJpaRepository;
import backend.univfit.domain.comment.entity.CommentEntity;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.exception.MemberException;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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
    public void createAnnouncementComments(CommentRequest commentRequest, Long announcementId, MemberInfoObject memberInfoObject) {
        Long memberId = memberInfoObject.getMemberId();
        Member member = memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        AnnouncementEntity ae = announcementJpaRepository.findById(announcementId)
                .orElseThrow(() -> new AnnouncementException(ANNOUNCEMENT_NOT_FOUND));

        CommentEntity comment = CommentRequest.toEntity(commentRequest.commentContents(), ae, member);
        commentJpaRepository.save(comment);
    }

    @Override
    public List<CommentResponse> getAnnouncementComments(Long announcementId) {
        AnnouncementEntity ae = announcementJpaRepository.findById(announcementId)
                .orElseThrow(() -> new AnnouncementException(ANNOUNCEMENT_NOT_FOUND));

        return commentJpaRepository.findByAnnouncementEntity(ae)
                .stream()
                .map(c -> {
                    Long memberId = c.getMember().getId();
                    String beforeTime = formatRelativeDate(c.getCreatedAt(), LocalDateTime.now());
                    return CommentResponse.of(memberId, c.getMember().getNickName(), c.getCommentContent(), beforeTime);
                }).toList();
    }
    private String formatRelativeDate(LocalDateTime createdAt, LocalDateTime now) {
        Period period = Period.between(createdAt.toLocalDate(), now.toLocalDate());
        long totalDays = ChronoUnit.DAYS.between(createdAt, now);
        long weeks = totalDays / 7;
        long hours = ChronoUnit.HOURS.between(createdAt, now);
        long minutes = ChronoUnit.MINUTES.between(createdAt, now);

        if (period.getYears() > 0) return period.getYears() + "년 전";
        else if (period.getMonths() > 0) return period.getMonths() + "개월 전";
        else if (weeks > 0) return weeks + "주 전";
        else if (totalDays > 0) return totalDays + "일 전";
        else if (hours > 0) return hours + "시간 전";
        else if (minutes > 0) return minutes + "분 전";
        else return "방금 전";
    }
}
