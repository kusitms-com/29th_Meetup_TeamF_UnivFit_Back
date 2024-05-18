package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.api.dto.response.AnnouncementResponse;
import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.LikeEntity;
import backend.univfit.domain.apply.exception.AnnouncementException;
import backend.univfit.domain.apply.exception.LikeException;
import backend.univfit.domain.apply.repository.AnnouncementJpaRepository;
import backend.univfit.domain.apply.repository.LikeJpaRepository;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.exception.MemberException;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static backend.univfit.global.error.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementLikeServiceImpl implements AnnouncementLikeService{
    private final LikeJpaRepository likeJpaRepository;
    private final AnnouncementJpaRepository announcementJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final AnnouncementManager announcementManager;

    @Override
    public void likeAnnouncement(Long announcementId/**, MemberInfoObject memberInfoObject**/) {
//        Long memberId = memberInfoObject.getMemberId();
        Long memberId = 1L;
        Member member = memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        AnnouncementEntity ae = announcementJpaRepository.findById(announcementId)
                .orElseThrow(() -> new AnnouncementException(ANNOUNCEMENT_NOT_FOUND));

        if (likeJpaRepository.findByAnnouncementEntityAndMember(ae, member).isPresent()) {
            throw new LikeException(LIKE_IS_EXIST);
        }

        likeJpaRepository.save(LikeEntity.of(null, ae, member));
    }

    @Override
    public void deleteLikeAnnouncement(Long announcementId/**, MemberInfoObject memberInfoObject**/) {
        Long memberId = 1L;
        Member member = memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        AnnouncementEntity ae = announcementJpaRepository.findById(announcementId)
                .orElseThrow(() -> new AnnouncementException(ANNOUNCEMENT_NOT_FOUND));

        likeJpaRepository.delete(likeJpaRepository.findByAnnouncementEntityAndMember(ae,member)
                .orElseThrow(() -> new LikeException(LIKE_ERROR)));
    }

    @Override
    public AnnouncementListResponse getLikeAnnouncementList() {
        Long memberId = 1L;

        Member member = memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        List<LikeEntity> byMember = likeJpaRepository.findByMember(member);
        List<AnnouncementResponse> list = byMember.stream()
                .map(le -> {
                    AnnouncementEntity ae = le.getAnnouncementEntity();
                    ae.updateStatus(LocalDate.now());
                    announcementJpaRepository.save(ae);

                    String announcementStatus = announcementManager.checkAnnouncementStatus(ae);
                    Long remainingDay = ChronoUnit.DAYS.between(LocalDate.now(), ae.getEndDocumentDate());
                    String applyPossible = announcementManager.checkEligibility(ae, 1L);

                    return AnnouncementResponse.of(ae.getId(), ae.getScholarShipImage(),
                            ae.getScholarShipName(), ae.getScholarShipFoundation(), announcementStatus,
                            ae.getApplicationPeriod(), remainingDay, applyPossible
                    );
                }).toList();


        return AnnouncementListResponse.of(list, list.size());
    }
}
