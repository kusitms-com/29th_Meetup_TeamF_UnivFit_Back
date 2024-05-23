package backend.univfit.domain.member.api;

import backend.univfit.domain.member.application.MemberService;
import backend.univfit.global.ApiResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import backend.univfit.global.dto.response.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberApi {
    private final MemberService memberService;
    @DeleteMapping("/application/{applicationId}")
    public ApiResponse<GeneralResponse> deleteMemberApplication(@MemberInfo MemberInfoObject mio,
                                                                @PathVariable(name = "applicationId") Long applicationId){
        return ApiResponse.onSuccess(memberService.deleteMemberApplication(mio, applicationId));
    }
}
