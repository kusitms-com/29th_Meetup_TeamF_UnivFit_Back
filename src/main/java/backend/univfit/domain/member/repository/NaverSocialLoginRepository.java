package backend.univfit.domain.member.repository;

import backend.univfit.domain.member.entity.NaverSocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NaverSocialLoginRepository extends JpaRepository<NaverSocialLogin, Long> {
    NaverSocialLogin findByNaverNumber(String naverNumber);
}
