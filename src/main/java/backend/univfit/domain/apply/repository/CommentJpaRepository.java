package backend.univfit.domain.apply.repository;

import backend.univfit.domain.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {
}
