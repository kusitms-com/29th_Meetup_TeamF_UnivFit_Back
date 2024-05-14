package backend.univfit.domain.apply.application.service;

import backend.univfit.domain.apply.application.api.dto.response.AnnouncementDetailResponse;
import backend.univfit.domain.apply.application.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.application.api.dto.response.AnnouncementResponse;
import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.ConditionEntity;
import backend.univfit.domain.apply.entity.enums.AnnouncementStatus;
import backend.univfit.domain.apply.entity.enums.ConditionMatch;
import backend.univfit.domain.apply.exception.AnnouncementException;
import backend.univfit.domain.apply.exception.ConditionException;
import backend.univfit.domain.apply.repository.AnnouncementJpaRepository;
import backend.univfit.domain.apply.repository.ConditionJpaRepository;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import backend.univfit.domain.member.repository.MemberPrivateInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static backend.univfit.global.error.status.ErrorStatus.ANNOUNCEMENT_NOT_FOUND;
import static backend.univfit.global.error.status.ErrorStatus.CONDITION_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementJpaRepository announcementJpaRepository;
    private final AnnouncementManager announcementManager;
    private final ConditionJpaRepository conditionJpaRepository;

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
     * @param announcementId
     * @return
     */
    @Override
    public AnnouncementResponse getAnnouncement(Long announcementId) {
        AnnouncementEntity ar = announcementJpaRepository.findById(announcementId)
                .orElseThrow(() -> new AnnouncementException(ANNOUNCEMENT_NOT_FOUND));

        String applicationConditions = ar.getApplicationConditions();

        Map<String, Boolean> checkPossibility = new HashMap<>();

//        checkPossibility.put();


        AnnouncementDetailResponse.of(ar.getScholarShipName(), ar.getScholarShipFoundation(), ar.getSupportAmount(),
                ar.getApplicationPeriod(), ar.getHashTag(), ar.getApplicationConditions(), ar.getDetailContents());
        return null;
    }
}
