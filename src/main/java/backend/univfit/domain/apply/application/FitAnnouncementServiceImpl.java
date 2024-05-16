package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.api.dto.response.AnnouncementResponse;
import backend.univfit.domain.apply.entity.enums.AnnouncementStatus;
import backend.univfit.domain.apply.repository.AnnouncementJpaRepository;
import backend.univfit.domain.apply.repository.ConditionJpaRepository;
import backend.univfit.domain.apply.repository.ScholarShipFoundationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FitAnnouncementServiceImpl implements FitAnnouncementService{
    private final AnnouncementJpaRepository announcementJpaRepository;
    private final AnnouncementManager announcementManager;
    private final ConditionJpaRepository conditionJpaRepository;
    //    private final MemberPrivateInfoJpaRepository memberPrivateInfoJpaRepository;
//    private final MemberJpaRepository memberJpaRepository;
    private final ScholarShipFoundationJpaRepository scholarShipFoundationJpaRepository;

    @Override
    public AnnouncementListResponse getAnnouncementList(String status) {
        List<AnnouncementResponse> list = announcementJpaRepository.findAll().stream()
                .map(ar -> {
                    ar.updateStatus(LocalDate.now());
                    announcementJpaRepository.save(ar);
                    return ar;
                })
                .map(ar -> {
                    String announcementStatus = announcementManager.checkAnnouncementStatus(ar);
                    long remainingDay = ChronoUnit.DAYS.between(LocalDate.now(), ar.getEndDocumentDate());
                    String remainingDaysToString = "D-" + remainingDay;
                    String applyPossible = announcementManager.checkEligibility(ar, 1L);

                    return AnnouncementResponse.of(ar.getId(),
                            ar.getScholarShipName(), ar.getScholarShipFoundation(), announcementStatus,
                            ar.getApplicationPeriod(), remainingDaysToString, applyPossible
                    );
                })//여기 고쳐야함
                .filter(ar -> {
                    if (status.equals("전체")) {
                        return ar.applyPossible().equals("판단불가") || ar.applyPossible().equals("지원대상");
                    } else if (status.equals("바로지원가능")) {
                        return ar.applyPossible().equals("지원대상");
                    } else {
                        return ar.scholarshipStatus().contains(status);
                    }
                })
                .toList();

        return AnnouncementListResponse.of(list, list.size());
    }


}
