package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.ConditionEntity;
import backend.univfit.domain.apply.entity.enums.AnnouncementStatus;
import backend.univfit.domain.apply.exception.ConditionException;
import backend.univfit.domain.apply.repository.ConditionJpaRepository;
import backend.univfit.domain.member.entity.MemberPrivateInfo;
import backend.univfit.domain.member.exception.MemberPrivateInfoException;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import backend.univfit.domain.member.repository.MemberPrivateInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static backend.univfit.global.error.status.ErrorStatus.CONDITION_NOT_FOUND;
import static backend.univfit.global.error.status.ErrorStatus.MEMBER_PRIVATE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AnnouncementManager {
    private final ConditionJpaRepository conditionJpaRepository;
    private final MemberPrivateInfoJpaRepository memberPrivateInfoJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    public String checkAnnouncementStatus(AnnouncementEntity announcementEntity) {
        if (!LocalDate.now().isBefore(announcementEntity.getStartApplyDate()) && !LocalDate.now().isAfter(announcementEntity.getEndDocumentDate())) {
            return String.valueOf(AnnouncementStatus.ING);
        } else if (LocalDate.now().isBefore(announcementEntity.getStartApplyDate())) {
            return String.valueOf(AnnouncementStatus.UPCOMING); // 현재 날짜가 공고 시작일 이전이면 공고 예정
        } else {
            return String.valueOf(AnnouncementStatus.FINISHED); //나머지 마감으로
        }
    }

    public String checkEligibility(AnnouncementEntity announcementEntity, Long memberId) {
        ConditionEntity condition = conditionJpaRepository.findByAnnouncementEntity(announcementEntity)
                .orElseThrow(() -> new ConditionException(CONDITION_NOT_FOUND));
        MemberPrivateInfo memberInfo = memberPrivateInfoJpaRepository.findById(memberId)
                .orElseThrow(() -> new MemberPrivateInfoException(MEMBER_PRIVATE_NOT_FOUND));

        return compareConditions(condition, memberInfo);
    }

    private String compareConditions(ConditionEntity condition, MemberPrivateInfo memberInfo) {
        try {
            if (!compareBasicInfo(condition, memberInfo) || !compareGrades(condition, memberInfo) || !compareIncome(condition, memberInfo)) {
                return "지원불가";
            }
            return "지원대상";
        } catch (NullPointerException e) {
            return "판단불가";
        }
    }

    private boolean compareBasicInfo(ConditionEntity condition, MemberPrivateInfo memberInfo) {
        // deptType을 콤마로 분리하여 리스트로 변환 후 포함 여부 확인
        boolean deptTypeMatch = true;
        if (condition.getDeptType() != null && !condition.getDeptType().isEmpty()) {
            List<String> allowedDeptTypes = Arrays.asList(condition.getDeptType().split("\\s*,\\s*"));
            deptTypeMatch = allowedDeptTypes.contains(memberInfo.getDeptType());
        }

        boolean underPrevilegedMatch = true;
        if (condition.getUnderPrivilegedInfo() != null && !condition.getUnderPrivilegedInfo().isEmpty()) {
            List<String> allowedUnderPrivilegedTypes = Arrays.asList(condition.getUnderPrivilegedInfo().split("\\s*,\\s*"));
            underPrevilegedMatch = allowedUnderPrivilegedTypes.contains(memberInfo.getUnderPrivilegedInfo());
        }

        return (condition.getSchoolType() == null || Objects.equals(condition.getSchoolType(), memberInfo.getSchoolType())) &&
                (condition.getSchoolName() == null || Objects.equals(condition.getSchoolName(), memberInfo.getSchoolName())) &&
                deptTypeMatch &&
                (condition.getIsPresent() == null || Objects.equals(condition.getIsPresent(), memberInfo.getIsPresent())) &&
                (condition.getSemester() == null || Objects.equals(condition.getSemester(), memberInfo.getSemester())) &&
                (condition.getResidence() == null || Objects.equals(condition.getResidence(), memberInfo.getResidence())) &&
                (condition.getResidenceType() == null || Objects.equals(condition.getResidenceType(), memberInfo.getResidenceType())) &&
                (condition.getGender() == null || Objects.equals(condition.getGender(), memberInfo.getGender())) &&
                (condition.getAge() == null || Objects.equals(condition.getAge(), memberInfo.getAge())) &&
                underPrevilegedMatch;
    }

    private boolean compareGrades(ConditionEntity condition, MemberPrivateInfo memberInfo) {
        boolean useThree = memberInfo.getLastGradeOfThree() != null;
        if (useThree) {
            return (condition.getTotalGradeOfThree() == null || (condition.getTotalGradeOfThree() <= memberInfo.getTotalGradeOfThree())) &&
                    (condition.getLastGradeOfThree() == null || (condition.getLastGradeOfThree() <= memberInfo.getLastGradeOfThree()));
        } else {
            return (condition.getTotalGradeOfFive() == null || (condition.getTotalGradeOfFive() <= memberInfo.getTotalGradeOfFive())) &&
                    (condition.getLastGradeOfFive() == null || (condition.getLastGradeOfFive() <= memberInfo.getLastGradeOfFive()));
        }
    }

    private boolean compareIncome(ConditionEntity condition, MemberPrivateInfo memberInfo) {
        return (condition.getIncomeQuality() == null || (condition.getIncomeQuality() >= memberInfo.getIncomeQuality())) &&
                (condition.getMonthlyIncome() == null || condition.getMonthlyIncome() >= memberInfo.getMonthlyIncome());
    }

}
