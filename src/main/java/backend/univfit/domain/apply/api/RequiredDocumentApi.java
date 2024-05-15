package backend.univfit.domain.apply.api;

import backend.univfit.domain.apply.api.dto.response.RequiredDocumentResponse;
import backend.univfit.domain.apply.application.AnnouncementService;
import backend.univfit.domain.apply.application.RequiredDocumentService;
import backend.univfit.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/announcements")
@RestController
public class RequiredDocumentApi {
    private final AnnouncementService announcementService;
    private final RequiredDocumentService requiredDocumentService;

    /**
     * 필요 서류 전체 조회
     * @param announcementId
     * @return
     */
    @GetMapping("/{announcementId}/required-documents")
    public ApiResponse<List<RequiredDocumentResponse>> getAllRequiredDocuments(@PathVariable Long announcementId) {
        return ApiResponse.onSuccess(requiredDocumentService.getRequiredDocumentList(announcementId));
    }

    /**
     * 필요 서류 세부 조회
     * @param announcementId
     * @param requiredDocumentId
     * @return
     */
    @GetMapping("/{announcementId}/required-documents/{requiredDocumentId}")
    public ApiResponse<RequiredDocumentResponse> getAllRequiredDocuments(@PathVariable Long announcementId,
                                                                         @PathVariable Long requiredDocumentId) {
        return ApiResponse.onSuccess(requiredDocumentService.getRequiredDocument(announcementId, requiredDocumentId));
    }
}
