package backend.univfit.global.Interceptor;

import backend.univfit.global.error.exception.JwtException;
import backend.univfit.global.utils.JwtUtils;
import backend.univfit.global.validator.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static backend.univfit.global.error.status.ErrorStatus.JWT_EXPIRED;
import static backend.univfit.global.error.status.ErrorStatus.JWT_INVALIED;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccessTokenInterceptor implements HandlerInterceptor {
    @Value("${jwt.secret}")
    private String secretKey;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userToken = TokenValidator.tokenValidator(request, response);
        String tokenType = JwtUtils.getTokenHeader(userToken).get("typ").toString();
        log.info(tokenType);

        if(!tokenType.equals("access_token")){
            //토큰 타입이 액세스 토큰이 아닐 경우
            throw new JwtException(JWT_INVALIED);
        }

        if(JwtUtils.isExpired(userToken, secretKey)){
            // 토큰이 만료되었다면 예외 던지기
            throw new JwtException(JWT_EXPIRED);
        }

        return true;
    }
}
