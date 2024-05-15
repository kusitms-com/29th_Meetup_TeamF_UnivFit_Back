package backend.univfit.domain.apply.repository;

import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.ScholarShipFoundationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScholarShipFoundationJpaRepository extends JpaRepository<ScholarShipFoundationEntity, Long> {
    Optional<ScholarShipFoundationEntity> findByAnnouncementEntity(AnnouncementEntity announcementEntity);
}
