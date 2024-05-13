package backend.univfit.member.api;

import backend.univfit.global.ApiResponse;
import backend.univfit.member.application.OnboardService;
import backend.univfit.member.dto.login.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static backend.univfit.global.ApiResponse.onSuccess;

@RestController
@RequiredArgsConstructor
public class OnboardApi {
    private final OnboardService onboardService;
    @PostMapping("/login/{socialServiceNickName}")
    public ApiResponse<LoginResponse> login(@PathVariable("socialServiceNickName") String sn,
                                            @RequestHeader("socialAccessToken") String accessToken) throws ParseException {
        return ApiResponse.onSuccess(onboardService.login(sn, accessToken));
    }
}
