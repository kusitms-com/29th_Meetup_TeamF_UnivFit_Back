package backend.univfit.domain.member.application;

import backend.univfit.domain.member.dto.response.MyPageResponse;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.exception.MemberException;
import backend.univfit.domain.member.repository.KakaoSocialLoginJpaRepository;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import backend.univfit.domain.member.repository.NaverSocialLoginJpaRepository;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static backend.univfit.global.error.status.ErrorStatus.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MemberJpaRepository memberJpaRepository;
    private KakaoSocialLoginJpaRepository kakaoSocialLoginJpaRepository;
    private NaverSocialLoginJpaRepository naverSocialLoginJpaRepository;

    public MyPageResponse getMyPage(MemberInfoObject memberInfoObject) {
        Long memberId = memberInfoObject.getMemberId();

        Member member = memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        return MyPageResponse.of(member.getNickName(), memberInfoObject.getSocialLoginInfo());
    }
}
