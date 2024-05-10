package backend.univfit.domain.apply.domain;

import backend.univfit.domain.apply.domain.enums.MontlyIncome;
import backend.univfit.domain.member.entity.enums.SchoolType;
import backend.univfit.domain.member.entity.enums.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ConditionEntity {
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
    private MontlyIncome montlyIncome;

    @OneToOne
    @JoinColumn(name = "apply_announcement_id")
    private ApplyAnnouncementEntity applyAnnouncementEntity;
}
