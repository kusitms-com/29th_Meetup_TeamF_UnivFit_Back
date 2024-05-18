package backend.univfit.domain.apply.repository;

import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.LikeEntity;
import backend.univfit.domain.member.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeJpaRepository extends JpaRepository<LikeEntity, Long> {
    List<LikeEntity> findByMember(Member member);
    Optional<LikeEntity> findByAnnouncementEntityAndMember(AnnouncementEntity announcementEntity, Member member);
    List<LikeEntity> findByAnnouncementEntity(AnnouncementEntity announcementEntity);

    @Query("SELECT a, COUNT(l) as likes FROM LikeEntity l JOIN l.announcementEntity a GROUP BY a ORDER BY likes DESC")
    List<Object[]> findTopPopularAnnouncements(Pageable pageable);
}
