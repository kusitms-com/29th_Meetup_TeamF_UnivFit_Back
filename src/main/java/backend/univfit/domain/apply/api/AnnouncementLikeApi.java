package backend.univfit.domain.apply.api;

import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.application.AnnouncementLikeService;
import backend.univfit.domain.apply.application.AnnouncementService;
import backend.univfit.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/announcements")
@RestController
public class AnnouncementLikeApi {
    private final AnnouncementLikeService announcementLikeService;

    /**
     * 장학금 공고 좋아요 누르기
     * @param announcementId
     * @return
     */
    @PostMapping("/{announcementId}/likes")
    public ApiResponse<?> likeAnnouncement(@PathVariable Long announcementId/**, @MemberInfo MemberInfoObject memberInfoObject**/) {
        announcementLikeService.likeAnnouncement(announcementId);
        return ApiResponse.onSuccess("성공적으로 좋아요가 반영되었습니다.");
    }

    @DeleteMapping("/{announcementId}/likes")
    public ApiResponse<?> deleteLikeAnnouncement(@PathVariable Long announcementId/**, @MemberInfo MemberInfoObject memberInfoObject**/) {
        announcementLikeService.deleteLikeAnnouncement(announcementId);
        return ApiResponse.onSuccess("성공적으로 좋아요가 삭제되었습니다.");
    }

    @GetMapping ("/likes")
    public ApiResponse<AnnouncementListResponse> getAnnouncementList(/**, @MemberInfo MemberInfoObject memberInfoObject**/) {
        return ApiResponse.onSuccess(announcementLikeService.getLikeAnnouncementList(/**memberInfoObject**/));
    }






}
