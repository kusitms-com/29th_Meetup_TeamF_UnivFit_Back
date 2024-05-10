package backend.univfit.member.application;

import backend.univfit.global.utils.JwtUtils;
import backend.univfit.global.utils.KakaoApi;
import backend.univfit.global.utils.NaverApi;
import backend.univfit.member.dto.login.response.LoginResponse;
import backend.univfit.member.entity.KakaoSocialLogin;
import backend.univfit.member.entity.Member;
import backend.univfit.member.entity.NaverSocialLogin;
import backend.univfit.member.repository.KakaoSocialLoginRepository;
import backend.univfit.member.repository.MemberRepository;
import backend.univfit.member.repository.NaverSocialLoginRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.MediaSize;
import java.util.HashMap;
import java.util.Map;

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


}
