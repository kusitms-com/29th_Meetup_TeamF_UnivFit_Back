package backend.univfit.domain.apply.api;

import backend.univfit.domain.apply.api.dto.request.ChangeApplyStatusRequest;
import backend.univfit.domain.apply.api.dto.response.ApplyListDetailResponse;
import backend.univfit.domain.apply.api.dto.response.ApplyListResponse;
import backend.univfit.domain.apply.application.ApplyListService;
import backend.univfit.global.ApiResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import backend.univfit.global.dto.response.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{applyId}")
    public ApiResponse<ApplyListDetailResponse> getApplyListDetail(@MemberInfo MemberInfoObject mio,
                                                             @PathVariable(name = "applyId") Long applyId){
        return ApiResponse.onSuccess(applyListService.getApplyListDetail(mio, applyId));
    }

    @PatchMapping("/apply-status/{applyId}")
    public ApiResponse<GeneralResponse> changeApplyStatus(@MemberInfo MemberInfoObject mio,
                                                          @PathVariable(name = "applyId") Long applyId,
                                                          @RequestBody ChangeApplyStatusRequest cas){
        return ApiResponse.onSuccess(applyListService.changeApplyStatus(mio, applyId, cas));
    }

}
