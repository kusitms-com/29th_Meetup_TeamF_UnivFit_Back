package backend.univfit.domain.member.dto.request;

import backend.univfit.domain.member.entity.enums.Gender;
import backend.univfit.domain.member.entity.enums.SchoolType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OnboardingRequest {
    @Enumerated(EnumType.STRING)
    private SchoolType schoolType;
    private String schoolName;
    private String schoolLocation;
    private String deptType;
    private String deptName;
    private Boolean isPresent;
    private Integer semester;
    private String residence;
    private Integer residenceType;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer birthYear;
    private String underPrivilegedInfo;
    private Float totalFullGrade;
    private Float totalGrade;
    private Float lastFullGrade;
    private Float lastGrade;
    private Integer incomeQuality;
    private Integer monthlyIncome;
    private Integer supportSection;
}
