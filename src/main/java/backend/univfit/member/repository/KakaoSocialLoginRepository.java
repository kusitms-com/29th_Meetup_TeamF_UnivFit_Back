package backend.univfit.member.repository;

import backend.univfit.member.entity.KakaoSocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoSocialLoginRepository extends JpaRepository<KakaoSocialLogin, Long> {
    KakaoSocialLogin findByKakaoNumber(String kakaoNumber);
}
