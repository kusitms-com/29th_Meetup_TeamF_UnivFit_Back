package backend.univfit.domain.apply.repository;

import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.ConditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConditionJpaRepository extends JpaRepository<ConditionEntity,Long> {
    Optional<ConditionEntity> findByAnnouncementEntity(AnnouncementEntity announcementEntity);
}
