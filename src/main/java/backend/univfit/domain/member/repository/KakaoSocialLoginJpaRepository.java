package backend.univfit.domain.member.repository;

import backend.univfit.domain.member.entity.KakaoSocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoSocialLoginJpaRepository extends JpaRepository<KakaoSocialLogin, Long> {
    KakaoSocialLogin findByKakaoNumber(String kakaoNumber);
}
