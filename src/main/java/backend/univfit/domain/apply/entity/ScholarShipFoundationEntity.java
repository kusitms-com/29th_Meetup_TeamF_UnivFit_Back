package backend.univfit.domain.apply.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "scholarship_foundation_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ScholarShipFoundationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3000)
    private String foundationInformation;

    @OneToOne
    @JoinColumn(name = "announcement_id")
    private AnnouncementEntity announcementEntity;

}
