package backend.univfit.domain.apply.api;

import backend.univfit.domain.apply.api.dto.response.AnnouncementListResponse;
import backend.univfit.domain.apply.application.AnnouncementLikeService;
import backend.univfit.domain.apply.application.AnnouncementService;
import backend.univfit.global.ApiResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/announcements")
@RestController
public class AnnouncementLikeApi {
    private final AnnouncementLikeService announcementLikeService;

    /**
     * 장학금 공고 좋아요(찜) 누르기
     * @param announcementId
     * @return
     */
    @PostMapping("/{announcementId}/likes")
    public ApiResponse<?> likeAnnouncement(@PathVariable Long announcementId, @MemberInfo MemberInfoObject memberInfoObject) {
        announcementLikeService.likeAnnouncement(announcementId, memberInfoObject);
        return ApiResponse.onSuccess("성공적으로 좋아요가 반영되었습니다.");
    }

    /**
     * 장학금 공고 좋아요(찜) 취소
     * @param announcementId
     * @return
     */
    @DeleteMapping("/{announcementId}/likes")
    public ApiResponse<?> deleteLikeAnnouncement(@PathVariable Long announcementId, @MemberInfo MemberInfoObject memberInfoObject) {
        announcementLikeService.deleteLikeAnnouncement(announcementId, memberInfoObject);
        return ApiResponse.onSuccess("성공적으로 좋아요가 삭제되었습니다.");
    }

    /**
     * 찜한 공고 조회
     * @return
     */
    @GetMapping ("/likes")
    public ApiResponse<AnnouncementListResponse> getAnnouncementList(@MemberInfo MemberInfoObject memberInfoObject) {
        return ApiResponse.onSuccess(announcementLikeService.getLikeAnnouncementList(memberInfoObject));
    }






}
