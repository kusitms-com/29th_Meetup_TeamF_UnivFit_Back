package backend.univfit.domain.member.dto.response;

import backend.univfit.domain.member.entity.enums.Gender;
import backend.univfit.domain.member.entity.enums.SchoolType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OnboardingResponse {
    private Integer schoolType;
    private String schoolName;
    private String schoolLocation;
    private String deptType;
    private String deptName;
    private Boolean isPresent;
    private Integer semester;
    private String residence;
    private Integer residenceType;
    private Integer gender;
    private Integer birthYear;
    private String underPrivilegedInfo;
    private Float totalFullGrade;
    private Float totalGrade;
    private Float lastFullGrade;
    private Float lastGrade;
    private Integer incomeQuality;
    private Integer monthlyIncome;
    private Integer supportSection;

    public static OnboardingResponse of(
            Integer schoolType,
            String schoolName,
            String schoolLocation,
            String deptType,
            String deptName,
            Boolean isPresent,
            Integer semester,
            String residence,
            Integer residenceType,
            Integer gender,
            Integer birthYear,
            String underPrivilegedInfo,
            Float totalFullGrade,
            Float totalGrade,
            Float lastFullGrade,
            Float lastGrade,
            Integer incomeQuality,
            Integer monthlyIncome,
            Integer supportSection
    ){
        return OnboardingResponse.builder()
                .schoolType(schoolType)
                .schoolName(schoolName)
                .schoolLocation(schoolLocation)
                .deptType(deptType)
                .deptName(deptName)
                .isPresent(isPresent)
                .semester(semester)
                .residence(residence)
                .residenceType(residenceType)
                .gender(gender)
                .birthYear(birthYear)
                .underPrivilegedInfo(underPrivilegedInfo)
                .totalFullGrade(totalFullGrade)
                .totalGrade(totalGrade)
                .lastFullGrade(lastFullGrade)
                .lastGrade(lastGrade)
                .incomeQuality(incomeQuality)
                .monthlyIncome(monthlyIncome)
                .supportSection(supportSection)
                .build();
    }
}
