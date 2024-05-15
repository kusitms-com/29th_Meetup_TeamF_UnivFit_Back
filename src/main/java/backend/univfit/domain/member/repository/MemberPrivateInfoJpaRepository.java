package backend.univfit.domain.member.repository;

import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.entity.MemberPrivateInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberPrivateInfoJpaRepository extends JpaRepository<MemberPrivateInfo, Long> {
}
