package backend.univfit.domain.member.application;

import backend.univfit.domain.member.dto.request.MakeNickNameRequest;
import backend.univfit.domain.member.dto.response.AccessTokenResponse;
import backend.univfit.domain.member.entity.KakaoSocialLogin;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.entity.NaverSocialLogin;
import backend.univfit.domain.member.repository.KakaoSocialLoginRepository;
import backend.univfit.domain.member.repository.MemberRepository;
import backend.univfit.domain.member.repository.NaverSocialLoginRepository;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.utils.JwtUtils;
import backend.univfit.global.utils.KakaoApi;
import backend.univfit.global.utils.NaverApi;
import backend.univfit.domain.member.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OnboardService {
    @Value("${jwt.secret}")
    private String jwtSecret;
    private final String KAKAO = "kakao";
    private final String NAVER = "naver";

    private final KakaoSocialLoginRepository kakaoSocialLoginRepository;
    private final NaverSocialLoginRepository naverSocialLoginRepository;
    private final MemberRepository memberRepository;


    public LoginResponse login(String sn, String accessToken) throws ParseException {
        String socialId = null;
        boolean isOnboarding = false;
        Member member = null;
        Long memberId = null;
        Long socialLoginId = null;
        String socialLoginPlatForm = null;
        int flag = 0;
        // 카카오 로그인 했을 경우
        if(sn.equals(KAKAO)){
            socialId = KakaoApi.getUserInfo(accessToken);
            KakaoSocialLogin kakaoSocialLogin = kakaoSocialLoginRepository.findByKakaoNumber(socialId);
            if(kakaoSocialLogin == null){
                kakaoSocialLogin = new KakaoSocialLogin();
                kakaoSocialLogin.setKakaoNumber(socialId);
                kakaoSocialLoginRepository.save(kakaoSocialLogin);
                socialLoginId = kakaoSocialLogin.getId();
                socialLoginPlatForm = KAKAO;
            }
            member = kakaoSocialLogin.getMember();

        }
        //네이버 로그인 했을 경우
        if(sn.equals(NAVER)){
            socialId = NaverApi.getResponse(accessToken);
            NaverSocialLogin naverSocialLogin = naverSocialLoginRepository.findByNaverNumber(socialId);
            if(naverSocialLogin == null){
                naverSocialLogin = new NaverSocialLogin();
                naverSocialLogin.setNaverNumber(socialId);
                naverSocialLoginRepository.save(naverSocialLogin);
                socialLoginId = naverSocialLogin.getId();
                socialLoginPlatForm = NAVER;
            }
            member = naverSocialLogin.getMember();
        }

        if(member != null){
            memberId = member.getId();
            if(member.getMemberPrivateInfo() != null){
                isOnboarding = true;
            }
        }

        String ServiceAccessToken = JwtUtils.createAccessToken(socialLoginPlatForm, socialLoginId, memberId, jwtSecret);
        String refreshToken = JwtUtils.createRefreshToken(jwtSecret);


        return LoginResponse.of(memberId,isOnboarding,ServiceAccessToken, refreshToken);

    }


    public AccessTokenResponse makeNickName(MakeNickNameRequest mnr, MemberInfoObject mio) {
        String nickName = mnr.getNickName();
        //중복체크
        if(isDuplicatedNickName(nickName)){
            throw
        }
    }

    public boolean isDuplicatedNickName(String nickName) {
        return memberRepository.findByNickName(nickName) != null;
    }


}
