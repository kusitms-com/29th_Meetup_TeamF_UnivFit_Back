package backend.univfit.domain.member.api;

import backend.univfit.domain.member.application.MemberService;
import backend.univfit.domain.member.application.MyPageService;
import backend.univfit.domain.member.dto.response.MyPageResponse;
import backend.univfit.global.ApiResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MyPageApi {
    private final MyPageService myPageService;

    /**
     * 마이페이지 조회
     * @param memberInfoObject
     * @return
     */
    @GetMapping("/my-page")
    public ApiResponse<MyPageResponse> getMyPage(@MemberInfo MemberInfoObject memberInfoObject) {
        return ApiResponse.onSuccess(myPageService.getMyPage(memberInfoObject));
    }

}
