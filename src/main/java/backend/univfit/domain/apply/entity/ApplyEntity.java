package backend.univfit.domain.apply.entity;

import backend.univfit.domain.apply.entity.enums.ApplyStatus;
import backend.univfit.domain.coverletter.entity.CoverLetterEntity;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "apply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
public class ApplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplyStatus applyStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "apply_announcement_id")
    private AnnouncementEntity announcementEntity;


    public static ApplyEntity of(Long id, ApplyStatus applyStatus, Member member, AnnouncementEntity announcementEntity) {
        return new ApplyEntity(id, applyStatus, member, announcementEntity);
    }

}
