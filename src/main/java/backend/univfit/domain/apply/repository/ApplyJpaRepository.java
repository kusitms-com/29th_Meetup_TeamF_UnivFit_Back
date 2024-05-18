package backend.univfit.domain.apply.repository;

import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.ApplyEntity;
import backend.univfit.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyJpaRepository extends JpaRepository<ApplyEntity, Long> {
    List<ApplyEntity> findAllByMember(Member member);
    void deleteApplyEntityByMemberAndId(Member member, Long id);

    ApplyEntity findByMemberAndAnnouncementEntity(Member member, AnnouncementEntity announcementEntity);
}
