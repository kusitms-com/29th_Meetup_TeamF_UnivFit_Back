package backend.univfit.domain.member.application;

import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.exception.AnnouncementException;
import backend.univfit.domain.apply.repository.AnnouncementJpaRepository;
import backend.univfit.domain.apply.repository.ApplyJpaRepository;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.dto.response.GeneralResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static backend.univfit.global.error.status.ErrorStatus.ANNOUNCEMENT_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberJpaRepository memberJpaRepository;
    private final ApplyJpaRepository applyJpaRepository;
    private final AnnouncementJpaRepository announcementJpaRepository;

    @Transactional
    public GeneralResponse deleteMemberApplication(MemberInfoObject mio, Long applicationId) {
        Member member = memberJpaRepository.findById(mio.getMemberId()).get();
        AnnouncementEntity announcement = announcementJpaRepository.findById(applicationId).orElseThrow(() -> new AnnouncementException(ANNOUNCEMENT_NOT_FOUND));

        applyJpaRepository.deleteByMemberAndAnnouncementEntity(member, announcement);
        return GeneralResponse.of();
    }
}
