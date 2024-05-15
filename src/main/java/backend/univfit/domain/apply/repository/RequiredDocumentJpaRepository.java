package backend.univfit.domain.apply.repository;

import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.RequiredDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequiredDocumentJpaRepository extends JpaRepository<RequiredDocumentEntity, Long> {
    List<RequiredDocumentEntity> findByAnnouncementEntity(AnnouncementEntity announcementEntity);
}
