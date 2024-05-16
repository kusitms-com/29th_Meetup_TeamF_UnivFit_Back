package backend.univfit.domain.apply.api;

import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.application.FitAnnouncementService;
import backend.univfit.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/announcements")
@RestController
public class FitAnnouncementApi {
    private final FitAnnouncementService fitAnnouncementService;

    @GetMapping("/recommendations")
    public ApiResponse<AnnouncementListResponse> getAnnouncementList(@RequestParam String status
                                                                     /**, @MemberInfo MemberInfoObject memberInfoObject**/) {
        return ApiResponse.onSuccess(fitAnnouncementService.getAnnouncementList(status));
    }

}
