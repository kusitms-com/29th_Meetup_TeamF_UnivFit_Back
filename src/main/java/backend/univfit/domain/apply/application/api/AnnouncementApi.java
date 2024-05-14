package backend.univfit.domain.apply.application.api;

import backend.univfit.domain.apply.application.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.application.service.AnnouncementService;
import backend.univfit.domain.apply.application.api.dto.response.AnnouncementResponse;
import backend.univfit.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/announcements")
@RestController
public class AnnouncementApi {
    private final AnnouncementService announcementService;

    @GetMapping("")
    public ApiResponse<AnnouncementListResponse> getAnnouncementList(@RequestParam(required = false, defaultValue = "ING") List<String> status/**,@RequestHeader("socialAccessToken") String accessToken**/) {
        return ApiResponse.onSuccess(announcementService.getAnnouncementList(status));
    }

    @GetMapping("/{announcementId}")
    public ApiResponse<AnnouncementResponse> getAnnouncement(@PathVariable Long announcementId) {
        return ApiResponse.onSuccess(announcementService.getAnnouncement(announcementId));
    }

}
