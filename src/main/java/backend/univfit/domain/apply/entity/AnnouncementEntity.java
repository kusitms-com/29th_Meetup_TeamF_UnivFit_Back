package backend.univfit.domain.apply.entity;

import backend.univfit.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "announcement")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AnnouncementEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hashTag;
    private String scholarShipName;
    private String scholarShipFoundation;
    private String foundationLink;
    private Boolean isCoverLetterNeed;

    @Lob
    private String detailContents;

    @Lob
    private String coverLetterQuestion;

    @Lob
    private String applicationConditions;

    @Lob
    private Integer supportAmount;

    @Lob
    private String applicationPeriod;

}
