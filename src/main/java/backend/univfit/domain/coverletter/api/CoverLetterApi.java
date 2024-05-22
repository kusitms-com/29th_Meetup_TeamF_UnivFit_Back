package backend.univfit.domain.coverletter.api;

import backend.univfit.domain.coverletter.api.dto.CoverLetterQuestionsResponse;
import backend.univfit.domain.coverletter.service.CoverLetterService;
import backend.univfit.global.ApiResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/members/cover-letters")
@RestController
public class CoverLetterApi {
    private final CoverLetterService coverLetterService;
    @GetMapping("/questions/{applyId}")
    public ApiResponse<CoverLetterQuestionsResponse> showCoverLetterQuestions (@PathVariable("applyId") Long applyId,
                                                                               @MemberInfo MemberInfoObject mio){
        return ApiResponse.onSuccess(coverLetterService.showCoverLetterQuestions(applyId, mio));
    }
}
