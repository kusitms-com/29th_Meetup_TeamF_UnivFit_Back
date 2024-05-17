package backend.univfit.domain.member.repository;

import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.entity.NaverSocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NaverSocialLoginJpaRepository extends JpaRepository<NaverSocialLogin, Long> {
    NaverSocialLogin findByNaverNumber(String naverNumber);

    Optional<NaverSocialLogin> findByMember(Member member);
}
