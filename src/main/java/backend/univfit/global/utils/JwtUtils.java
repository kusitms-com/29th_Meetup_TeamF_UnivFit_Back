package backend.univfit.global.utils;

import backend.univfit.global.error.exception.JwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;

import java.time.Duration;
import java.util.*;

import static backend.univfit.global.error.status.ErrorStatus.JWT_EXPIRED;
import static backend.univfit.global.error.status.ErrorStatus.JWT_INVALIED;

@Slf4j
public class JwtUtils {
    public static String createAccessToken(String socialLoginInfo, Long socialPK, Long memberId, String secretKey){
        Long expiredMs = Duration.ofHours(6).toMillis(); // 만료 시간 6시간
        //token에 들어있는 유저 정보를 사용하기 위함
        // token에 유저 정보 담기 위해 claim사용
        Claims claims = Jwts.claims();
        claims.put("socialLoginInfo", socialLoginInfo);
        claims.put("socialPK", socialPK);
        claims.put("memberId", memberId);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, "access_token")
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact(); //jwt토큰 발행
    }

    /**
     * 리프레쉬 토큰을 발급해주는 메소드이다.
     * @param secretKey 토큰을 디코딩하기 위해 쓰이는 시크릿키
     * @param expiredMs 만료시간 설정(2주로 설정함)
     * @return
     */
    public static String createRefreshToken(String secretKey){
        Long expiredMs = Duration.ofDays(14).toMillis();
        Claims claims = Jwts.claims();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, "refresh_token")
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact(); //jwt토큰 발행
    }

    public static Map<String, Object> getTokenHeader(String token){
        JsonParser jsonParser = new BasicJsonParser();
        Map<String, String> requestInfo = getTokenInfo(token);
        log.info(requestInfo.toString());

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String decodedHeader = new String(decoder.decode(requestInfo.get("header")));

        Map<String, Object> headerArray = jsonParser.parseMap(decodedHeader);
        log.info(headerArray.toString());
        return headerArray;
    }

    public static Map<String, String> getTokenInfo(String token){

        StringTokenizer st = new StringTokenizer(token, ".");

        Map<String, String> requestInfo = new HashMap<>();

        requestInfo.put("header",st.nextToken());
        requestInfo.put("payload", st.nextToken());

        return requestInfo;
    }

    public static boolean isExpired(String token, String secretKey){
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey)
                    .build().parseClaimsJws(token).getBody()
                    .getExpiration().before(new Date()); // expired 된게 지금보다 전인가? -> 그러면 만료된거임
        }catch (ExpiredJwtException e){
            throw new JwtException(JWT_EXPIRED);
        }catch (SignatureException e){
            throw new JwtException(JWT_INVALIED);
        }
    }

    public static String getSocialLoginInfo(String token, String secretKey){
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("socialLoginInfo", String.class);
    }

    public static Long getSocialPK(String token, String secretKey){
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("socialPK", Long.class);
    }

    public static Long getMemberId(String token, String secretKey){
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("memberId", Long.class);
    }
}
