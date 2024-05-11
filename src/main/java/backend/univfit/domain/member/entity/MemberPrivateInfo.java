package backend.univfit.domain.member.entity;

import backend.univfit.domain.member.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPrivateInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberPrivateInfo_id")
    private Long id;

    private String schoolName;
    private String deptType;
    private String deptName;
    private Boolean inPresent;
    private Integer semester;
    private String residence;
    private ResidenceType residenceType;
    private Gender gender;
    private Integer age;
    private UnderPriviledGedInfo underPriviledGedInfo;
    private Float grade;
    private FullGrade fullGrade;
    private IncomeQuality incomeQuality;
    private Integer monthlyIncome;
    private SupportSection supportSection;
}