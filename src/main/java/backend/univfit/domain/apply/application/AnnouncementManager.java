package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.ConditionEntity;
import backend.univfit.domain.apply.entity.enums.AnnouncementStatus;
import backend.univfit.domain.apply.exception.ConditionException;
import backend.univfit.domain.apply.repository.ConditionJpaRepository;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.entity.MemberPrivateInfo;
import backend.univfit.domain.member.exception.MemberException;
import backend.univfit.domain.member.exception.MemberPrivateInfoException;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import backend.univfit.domain.member.repository.MemberPrivateInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static backend.univfit.global.error.status.ErrorStatus.*;

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
            return String.valueOf(AnnouncementStatus.UPCOMING);
        } else {
            return String.valueOf(AnnouncementStatus.FINISHED);
        }
    }

    public String checkEligibility(AnnouncementEntity announcementEntity, Long memberId) {
        ConditionEntity condition = conditionJpaRepository.findByAnnouncementEntity(announcementEntity)
                .orElseThrow(() -> new ConditionException(CONDITION_NOT_FOUND));
        Member member = memberJpaRepository.findById(memberId).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        Long id = member.getMemberPrivateInfo().getId();
        System.out.println("id = " + id);
        MemberPrivateInfo memberInfo = memberPrivateInfoJpaRepository.findById(id)
                .orElseThrow(() -> new MemberPrivateInfoException(MEMBER_PRIVATE_NOT_FOUND));

        return compareConditions(condition, memberInfo);
    }

    private String compareConditions(ConditionEntity condition, MemberPrivateInfo memberInfo) {
        boolean basicInfoMatch = compareBasicInfo(condition, memberInfo);
        System.out.println("basicInfoMatch = " + basicInfoMatch);
        boolean gradesMatch = compareGrades(condition, memberInfo);
        System.out.println("gradesMatch = " + gradesMatch);
        boolean incomeMatch = compareIncome(condition, memberInfo);
        System.out.println("incomeMatch = " + incomeMatch);

        if (basicInfoMatch || gradesMatch || incomeMatch) {
            return "지원불가";
        }
        if (
                (condition.getDeptType() != null && memberInfo.getDeptType() == null) ||
                (condition.getUnderPrivilegedInfo() != null && memberInfo.getUnderPrivilegedInfo() == null) ||
                (condition.getSemester() != null && memberInfo.getSemester() == null) ||
                (condition.getSchoolType() != null && memberInfo.getSchoolType() == null) ||
                (condition.getSchoolName() != null && memberInfo.getSchoolName() == null) ||
                (condition.getIsPresent() != null && memberInfo.getIsPresent() == null) ||
                (condition.getResidence() != null && memberInfo.getResidence() == null) ||
                (condition.getResidenceType() != null && memberInfo.getResidenceType() == null) ||
                (condition.getGender() != null && memberInfo.getGender() == null) ||
                (condition.getAge() != null && memberInfo.getBirthYear() == null) ||
                (condition.getMonthlyIncome() != null && memberInfo.getMonthlyIncome() == null) ||
                (condition.getIncomeQuality() != null && memberInfo.getIncomeQuality() == null) ||
                (condition.getSupportSection() != null && memberInfo.getSupportSection() == null) ||
                (condition.getExceptionValue() != null)) {
            return "판단불가";
        }
        return "지원대상";
    }

    private boolean compareBasicInfo(ConditionEntity condition, MemberPrivateInfo memberInfo) {
        boolean deptTypeMatch = false;
        if (condition.getDeptType() != null && !condition.getDeptType().isEmpty()) {
            List<String> allowedDeptTypes = Arrays.asList(condition.getDeptType().split("\\s*,\\s*"));
            deptTypeMatch = allowedDeptTypes.contains(memberInfo.getDeptType());
        }

        boolean underPrevilegedMatch = false;
        if (condition.getUnderPrivilegedInfo() != null && !condition.getUnderPrivilegedInfo().isEmpty()) {
            List<String> allowedUnderPrivilegedTypes = Arrays.asList(condition.getUnderPrivilegedInfo().split("\\s*,\\s*"));
            underPrevilegedMatch = allowedUnderPrivilegedTypes.contains(memberInfo.getUnderPrivilegedInfo());
        }

        boolean semesterMatch = false;
        if (condition.getSemester() != null && !condition.getSemester().isEmpty()) {
            List<String> semester = Arrays.asList(condition.getSemester().split("\\s*,\\s*"));
            semesterMatch = semester.contains(String.valueOf(memberInfo.getSemester()));
        }

        int currentYear = Year.now().getValue();
        int age = currentYear - memberInfo.getBirthYear();

        return (condition.getSchoolType() == null || Objects.equals(condition.getSchoolType(), memberInfo.getSchoolType())) &&
                (condition.getSchoolName() == null || Objects.equals(condition.getSchoolName(), memberInfo.getSchoolName())) &&
                deptTypeMatch &&
                (condition.getIsPresent() == null || Objects.equals(condition.getIsPresent(), memberInfo.getIsPresent())) &&
                semesterMatch &&
                (condition.getResidence() == null || Objects.equals(condition.getResidence(), memberInfo.getResidence())) &&
                (condition.getResidenceType() == null || Objects.equals(condition.getResidenceType(), memberInfo.getResidenceType())) &&
                (condition.getGender() == null || Objects.equals(condition.getGender(), memberInfo.getGender())) &&
                (condition.getAge() == null || (condition.getAge() >= age)) &&
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
                (condition.getSupportSection()==null || (condition.getSupportSection() >= memberInfo.getSupportSection())) &&
                (condition.getMonthlyIncome() == null || condition.getMonthlyIncome() >= memberInfo.getMonthlyIncome());
    }

}
