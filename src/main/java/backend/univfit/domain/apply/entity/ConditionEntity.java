package backend.univfit.domain.apply.entity;

import backend.univfit.domain.apply.entity.enums.MonthlyIncome;
import backend.univfit.domain.member.entity.enums.ResidenceType;
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
public class ConditionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String schoolLocation;
    private SchoolType schoolType;
    private String schoolName;
    private String deptType;
    private String deptName;
    private Boolean isPresent;
    private Integer semester;
    private String residence;
    private ResidenceType residenceType;
    private Gender gender;
    private Integer age;
    private UnderPriviledGedInfo underPriviledGedInfo;
    private String lastGrade;
    private String totalGrade;
    private IncomeQuality incomeQuality;
    private SupportSection supportSection;
    private MonthlyIncome monthlyIncome;

    @OneToOne
    @JoinColumn(name = "apply_announcement_id")
    private ApplyAnnouncementEntity applyAnnouncementEntity;
}
