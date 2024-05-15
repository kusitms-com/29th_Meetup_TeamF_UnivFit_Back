package backend.univfit.domain.member.api;

import backend.univfit.domain.member.dto.request.MakeNickNameRequest;
import backend.univfit.domain.member.dto.request.OnboardingRequest;
import backend.univfit.domain.member.dto.response.AccessTokenResponse;
import backend.univfit.global.ApiResponse;
import backend.univfit.domain.member.application.OnboardService;
import backend.univfit.domain.member.dto.response.LoginResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import backend.univfit.global.dto.response.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/onboards/nick-name")
    public ApiResponse<AccessTokenResponse> makeNickName(@RequestBody MakeNickNameRequest mnr,
                                                         @MemberInfo MemberInfoObject mio){
        return ApiResponse.onSuccess(onboardService.makeNickName(mnr, mio));
    }

    @PostMapping("/onboards")
    public ApiResponse<GeneralResponse> onboarding(@RequestBody OnboardingRequest obr,
                                                   @MemberInfo MemberInfoObject mio){
        return ApiResponse.onSuccess(onboardService.onboarding(obr, mio));
    }
}
