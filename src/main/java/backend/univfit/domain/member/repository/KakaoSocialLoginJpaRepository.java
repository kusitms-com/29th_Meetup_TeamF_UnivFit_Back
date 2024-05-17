package backend.univfit.domain.member.repository;

import backend.univfit.domain.member.entity.KakaoSocialLogin;
import backend.univfit.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KakaoSocialLoginJpaRepository extends JpaRepository<KakaoSocialLogin, Long> {
    KakaoSocialLogin findByKakaoNumber(String kakaoNumber);

    Optional<KakaoSocialLogin> findByMember(Member member);
}
