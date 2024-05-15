package backend.univfit.domain.apply.entity;

import backend.univfit.domain.apply.entity.enums.AnnouncementStatus;
import backend.univfit.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcement_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AnnouncementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hashTag;
    private String scholarShipName;
    private String scholarShipFoundation;
    private String foundationLink;
    private Boolean isCoverLetterNeed;
    private LocalDate endDocumentDate; //서류마감일
    private LocalDate startApplyDate;  //지원시작일
    private LocalDate endFinalPassDate;   //최종합격발표일

    @Enumerated(EnumType.STRING)
    private AnnouncementStatus announcementStatus;

    @Lob
    @Column(length = 2000)
    private String detailContents;

    @Lob
    @Column(length = 700)
    private String coverLetterQuestion;

    @Lob
    private String applicationConditions;

    @Lob
    private Integer supportAmount;

    @Lob
    private String applicationPeriod; //지원기간


    public void updateStatus(LocalDate currentDate) {
        if (currentDate.isBefore(startApplyDate)) {
            this.announcementStatus = AnnouncementStatus.UPCOMING;
        } else if (currentDate.isAfter(endDocumentDate)) {
            this.announcementStatus = AnnouncementStatus.FINISHED;
        } else {
            this.announcementStatus = AnnouncementStatus.ING;
        }
    }

}
