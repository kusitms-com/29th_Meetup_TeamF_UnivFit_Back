package backend.univfit.domain.member.repository;

import backend.univfit.domain.member.entity.MemberPrivateInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPrivateInfoJpaRepository extends JpaRepository<MemberPrivateInfo, Long> {

}
