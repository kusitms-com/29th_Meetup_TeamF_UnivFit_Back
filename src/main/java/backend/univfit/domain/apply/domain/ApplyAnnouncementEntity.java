package backend.univfit.domain.apply.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "apply_announcement")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ApplyAnnouncementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hashTag;
    private String scholarShipName;
    private String scholarShipFoundation;
    private String foundationLink;
    private Boolean isCoverLetterNeed;
    private String coverLetterQuestion;
    private String applicationConditions;
    private Integer supportAmount;
    private String applicationPeriod;

}
