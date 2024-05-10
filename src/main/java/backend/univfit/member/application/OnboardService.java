package backend.univfit.member.application;

import backend.univfit.global.utils.KakaoApi;
import backend.univfit.global.utils.NaverApi;
import backend.univfit.member.dto.login.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OnboardService {
    private final String KAKAO = "kakao";
    private final String NAVER = "naver";
    private final String SOCIAL_ACCESSTOKEN = "socialAccessToken";

    /**
     * 네이버 로그인 테스트
     */


    public LoginResponse login(String sn, String accessToken) throws ParseException {
        String socialId = null;
        // 카카오 로그인 했을 경우
        if(sn.equals(KAKAO)){
            socialId = KakaoApi.getUserInfo(accessToken);
        }
        //네이버 로그인 했을 경우
        if(sn.equals(NAVER)){
            socialId = NaverApi.getResponse(accessToken);
        }
        
        log.info("social Id = {}", socialId);

        return LoginResponse.of(true,2L,"", "");

    }


}
