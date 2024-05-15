package backend.univfit.domain.apply.api;

import backend.univfit.domain.apply.api.dto.request.CommentRequest;
import backend.univfit.domain.apply.application.AnnouncementCommentService;
import backend.univfit.domain.apply.application.AnnouncementService;
import backend.univfit.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/announcements")
@RestController
public class CommentAnnouncementApi {
    private final AnnouncementCommentService announcementCommentService;


    @PostMapping("/{announcementId}/comments")
    public ApiResponse<?> createAnnouncementComments(@RequestBody CommentRequest commentRequest, @PathVariable Long announcementId) {
        announcementCommentService.createAnnouncementComments(commentRequest, announcementId);
        return ApiResponse.onSuccess("댓글이 성공적으로 생성되었습니다.");
    }



}
