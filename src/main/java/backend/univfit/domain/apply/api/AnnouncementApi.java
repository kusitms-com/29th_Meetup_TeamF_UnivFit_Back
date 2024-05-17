package backend.univfit.domain.apply.api;

import backend.univfit.domain.apply.api.dto.response.AnnouncementDetailResponse;
import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.api.dto.response.ScholarShipFoundationResponse;
import backend.univfit.domain.apply.application.AnnouncementService;
import backend.univfit.domain.apply.api.dto.response.AnnouncementResponse;
import backend.univfit.global.ApiResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/announcements")
@RestController
public class AnnouncementApi {
    private final AnnouncementService announcementService;

    /**
     * 전체 장학금 전체 조회
     * @param status
     * @return
     */
    @GetMapping("")
    public ApiResponse<AnnouncementListResponse> getAnnouncementList(@RequestParam(required = false, defaultValue = "ING") List<String> status/**,@RequestHeader("socialAccessToken") String accessToken**/) {
        return ApiResponse.onSuccess(announcementService.getAnnouncementList(status));
    }

    /**
     * 전체 장학금 세부정보 조회
     * @param announcementId
     * @return
     */
    @GetMapping("/{announcementId}")
    public ApiResponse<AnnouncementDetailResponse> getAnnouncement(@PathVariable Long announcementId
                                                                   /**,@MemberInfo MemberInfoObject memberInfoObject**/) {
        return ApiResponse.onSuccess(announcementService.getAnnouncement(announcementId/**,memberInfoObject**/));
    }

    /**
     * 장학금 공고 저장
     * @param announcementId
     * @return
     */
    @PostMapping("/{announcementId}")
    public ApiResponse<?> saveAnnouncement(/**@MemberInfo MemberInfoObject memberInfoObject,**/
                                           @PathVariable Long announcementId) {
        announcementService.saveAnnouncement(announcementId/*,memberInfoObject*/);
        return ApiResponse.onSuccess("장학금이 성공적으로 저장되었습니다.");

    }

    /**
     * 재단 정보 조회
     * @param announcementId
     * @return
     */
    @GetMapping("/{announcementId}/scholarship-foundations")
    public ApiResponse<ScholarShipFoundationResponse> getScholarShipFoundationContents(@PathVariable Long announcementId) {
        return ApiResponse.onSuccess(announcementService.getScholarShipFoundationContents(announcementId));
    }

}