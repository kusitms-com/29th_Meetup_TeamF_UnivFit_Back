package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.AnnouncementDetailResponse;
import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.api.dto.response.AnnouncementResponse;
import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.ConditionEntity;
import backend.univfit.domain.apply.entity.enums.AnnouncementStatus;
import backend.univfit.domain.apply.exception.AnnouncementException;
import backend.univfit.domain.apply.exception.ConditionException;
import backend.univfit.domain.apply.repository.AnnouncementJpaRepository;
import backend.univfit.domain.apply.repository.ConditionJpaRepository;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.entity.MemberPrivateInfo;
import backend.univfit.domain.member.exception.MemberException;
import backend.univfit.domain.member.exception.MemberPrivateInfoException;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import backend.univfit.domain.member.repository.MemberPrivateInfoJpaRepository;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static backend.univfit.global.error.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementJpaRepository announcementJpaRepository;
    private final AnnouncementManager announcementManager;
    private final ConditionJpaRepository conditionJpaRepository;
    private final MemberPrivateInfoJpaRepository memberPrivateInfoJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    /**
     * 전체장학금 조회
     * 아직 지원대상인지, 지원불가인지, 판단불가인지는 안함
     * @return
     */
    @Override
    public AnnouncementListResponse getAnnouncementList(List<String> statuses/**,Long memberId**/) {
        List<String> finalStatuses = (statuses == null || statuses.isEmpty())
                ? List.of(AnnouncementStatus.ING.name())
                : new ArrayList<>(statuses);

        List<AnnouncementResponse> list = announcementJpaRepository.findAll().stream()
                .map(ar -> {
                    ar.updateStatus(LocalDate.now());
                    announcementJpaRepository.save(ar);
                    return ar;
                })
                .filter(f -> finalStatuses.contains(f.getAnnouncementStatus().name()))
                .map(ar -> {
                    String announcementStatus = announcementManager.checkAnnouncementStatus(ar);
                    long remainingDay = ChronoUnit.DAYS.between(LocalDate.now(), ar.getEndDocumentDate());
                    String remainingDaysToString = "D-" + remainingDay;
                    String applyPossible = announcementManager.checkEligibility(ar, 1L);

                    return AnnouncementResponse.of(
                            ar.getScholarShipName(), ar.getScholarShipFoundation(), announcementStatus,
                            ar.getApplicationPeriod(), remainingDaysToString, applyPossible
                    );
                }).toList();

        return AnnouncementListResponse.of(list, list.size());
    }

    /**
     * 세부장학금 조회
     *
     * @param announcementId
     * @return
     */
    @Override
    public AnnouncementDetailResponse getAnnouncement(Long announcementId/**, MemberInfoObject memberInfoObject**/) {
//        Long memberId = memberInfoObject.getMemberId();

        AnnouncementEntity ae = announcementJpaRepository.findById(announcementId)
                .orElseThrow(() -> new AnnouncementException(ANNOUNCEMENT_NOT_FOUND));

        ConditionEntity ce = conditionJpaRepository.findByAnnouncementEntity(ae)
                .orElseThrow(() -> new ConditionException(CONDITION_NOT_FOUND));

//        MemberPrivateInfo memberPrivateInfo = member.getMemberPrivateInfo();
//        String applicationConditions = ae.getApplicationConditions();
//        Map<String, Boolean> checkPossibility = new HashMap<>();

        ae.updateStatus(LocalDate.now());
        announcementJpaRepository.save(ae);
        long remainingDay = ChronoUnit.DAYS.between(LocalDate.now(), ae.getEndDocumentDate());
        String remainingDaysToString = "D-" + remainingDay;
        String applyPossible = announcementManager.checkEligibility(ae, 2L);
        String supportAmount = ae.getSupportAmount() + "만원";
        List<String> applyCondition = Arrays.stream(ae.getApplicationConditions().split(",")).toList();

        return AnnouncementDetailResponse.of(ae.getScholarShipName(), ae.getScholarShipFoundation(),
                remainingDaysToString, applyPossible, supportAmount, ae.getApplicationPeriod(), ae.getHashTag(), applyCondition, ae.getDetailContents());
    }
}
