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
@Table(name = "member_private_info")
public class MemberPrivateInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberPrivateInfo_id")
    private Long id;

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
}