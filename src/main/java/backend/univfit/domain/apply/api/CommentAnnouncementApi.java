package backend.univfit.domain.apply.api;

import backend.univfit.domain.apply.api.dto.request.CommentRequest;
import backend.univfit.domain.apply.api.dto.response.CommentResponse;
import backend.univfit.domain.apply.application.AnnouncementCommentService;
import backend.univfit.domain.apply.application.AnnouncementService;
import backend.univfit.global.ApiResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/announcements")
@RestController
public class CommentAnnouncementApi {
    private final AnnouncementCommentService announcementCommentService;


    /**
     * 공고 내 댓글 작성 API
     *
     * @param commentRequest
     * @param announcementId
     * @return
     */
    @PostMapping("/{announcementId}/comments")
    public ApiResponse<?> createAnnouncementComments(@RequestBody CommentRequest commentRequest, @PathVariable Long announcementId
                                                     /**, @MemberInfo MemberInfoObject memberInfoObject**/) {
        announcementCommentService.createAnnouncementComments(commentRequest, announcementId);
        return ApiResponse.onSuccess("댓글이 성공적으로 생성되었습니다.");
    }

    @GetMapping("/{announcementId}/comments")
    public ApiResponse<List<CommentResponse>> getAnnouncementComments(@PathVariable Long announcementId) {
        return ApiResponse.onSuccess(announcementCommentService.getAnnouncementComments(announcementId));

    }
}
