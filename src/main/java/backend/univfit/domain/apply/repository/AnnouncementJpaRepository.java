package backend.univfit.domain.apply.repository;

import backend.univfit.domain.apply.entity.AnnouncementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementJpaRepository extends JpaRepository<AnnouncementEntity, Long> {
    @Query("""
                    SELECT ae
                    FROM AnnouncementEntity ae
                    WHERE :q IS NULL OR ae.scholarShipName LIKE %:q%  
                    ORDER BY ae.id DESC
            """)
    List<AnnouncementEntity> findBySearchCriteria(@Param("q") String q);


}

