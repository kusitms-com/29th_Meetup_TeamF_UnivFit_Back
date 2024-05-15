package backend.univfit.domain.member.repository;

import backend.univfit.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Member findByNickName(String nickName);
}
