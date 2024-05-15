package backend.univfit.domain.apply.entity;

import backend.univfit.domain.apply.entity.enums.MonthlyIncome;
import backend.univfit.domain.member.entity.enums.SchoolType;
import backend.univfit.domain.member.entity.enums.*;
import backend.univfit.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "condition_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ConditionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String schoolLocation;

    @Enumerated(EnumType.STRING)
    private SchoolType schoolType;

    private String schoolName;
    private String deptType;
    private String deptName;
    private Boolean isPresent;
    private Integer semester;
    private String residence;

    @Enumerated(EnumType.STRING)
    private ResidenceType residenceType;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;
    private String underPrivilegedInfo;
    private Float totalGradeOfFive;
    private Float lastGradeOfFive;
    private Float totalGradeOfThree;
    private Float lastGradeOfThree;

    private Integer incomeQuality;
    private Integer monthlyIncome;

    @Enumerated(EnumType.STRING)
    private SupportSection supportSection;

    private String exceptionValue;

    @OneToOne
    @JoinColumn(name = "apply_announcement_id")
    private AnnouncementEntity announcementEntity;
}
