package backend.univfit.domain.clova.api;


import backend.univfit.domain.clova.api.dto.request.FoundationSummaryRequest;
import backend.univfit.domain.clova.api.dto.response.FoundationSummaryResponse;
import backend.univfit.domain.clova.application.ClovaService;
import backend.univfit.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClovaController {
    private final ClovaService clovaService;

    /**
     * 재단 정보 요약
     * @param request
     * @return
     */
    @PostMapping("/foundation/summary")
    public ApiResponse<FoundationSummaryResponse> testClova(@RequestBody FoundationSummaryRequest request) {
        return ApiResponse.onSuccess(FoundationSummaryResponse.of(
                clovaService.summarizeText(request))
        );
    }
}