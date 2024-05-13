package backend.univfit.global.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;

public class JwtUtils {
    public static String createAccessToken(String socialLoginInfo, Long socialPK, Long memberId, String secretKey){
        Long expiredMs = Duration.ofHours(2).toMillis(); // 만료 시간 2시간
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
}
