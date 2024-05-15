package backend.univfit.domain.document.repository;

import backend.univfit.domain.document.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentJpaRepository extends JpaRepository<DocumentEntity, Long> {
    Optional<DocumentEntity> findByDocumentName(String documentName);

}
