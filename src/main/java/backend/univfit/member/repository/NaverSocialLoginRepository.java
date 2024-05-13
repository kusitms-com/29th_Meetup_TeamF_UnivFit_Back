package backend.univfit.member.repository;

import backend.univfit.member.entity.NaverSocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NaverSocialLoginRepository extends JpaRepository<NaverSocialLogin, Long> {
    NaverSocialLogin findByNaverNumber(String naverNumber);
}
