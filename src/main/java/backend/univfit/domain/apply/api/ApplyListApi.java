package backend.univfit.domain.apply.api;

import backend.univfit.domain.apply.api.dto.response.ApplyListDetailResponse;
import backend.univfit.domain.apply.api.dto.response.ApplyListResponse;
import backend.univfit.domain.apply.application.ApplyListService;
import backend.univfit.global.ApiResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/apply-list")
@RestController
public class ApplyListApi {
    private final ApplyListService applyListService;
    @GetMapping("/all")
    public ApiResponse<ApplyListResponse> getAllApplyList(@MemberInfo MemberInfoObject mio){
        return ApiResponse.onSuccess(applyListService.getAllApplyList(mio));
    }

    @GetMapping("/pass")
    public ApiResponse<ApplyListResponse> getPassApplyList(@MemberInfo MemberInfoObject mio){
        return ApiResponse.onSuccess(applyListService.getPassApplyList(mio));
    }


    @GetMapping("/fail")
    public ApiResponse<ApplyListResponse> getFailApplyList(@MemberInfo MemberInfoObject mio){
        return ApiResponse.onSuccess(applyListService.getFailApplyList(mio));
    }

    @GetMapping("/{announcementId}")
    public ApiResponse<ApplyListDetailResponse> getApplyListDetail(@MemberInfo MemberInfoObject mio,
                                                             @PathVariable(name = "announcementId") Long announcementId){
        return ApiResponse.onSuccess(applyListService.getApplyListDetail(mio, announcementId));
    }

}
