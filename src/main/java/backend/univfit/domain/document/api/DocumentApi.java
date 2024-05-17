package backend.univfit.domain.document.api;

import backend.univfit.domain.document.api.dto.request.CreateDocumentRequest;
import backend.univfit.domain.document.api.dto.response.DocumentListResponse;
import backend.univfit.domain.document.application.DocumentService;
import backend.univfit.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class DocumentApi {
    private final DocumentService documentService;

    /**
     * 내 서류 등록
     * @param createDocumentRequest
     * @return
     */
    @PostMapping("/documents")
    public ApiResponse<?> createDocuments(/**@MemberInfo MemberInfoObject memberInfoObject,**/
                                          @RequestBody CreateDocumentRequest createDocumentRequest) {
        documentService.createDocuments(/**memberInfoObject,**/ createDocumentRequest);
        return ApiResponse.onSuccess("서류가 성공적으로 등록 되었습니다.");
    }

    /**
     * 내가 보유한 서류 전체 조회
     * @return
     */
    @GetMapping("/documents")
    public ApiResponse<DocumentListResponse> createDocuments(/**@MemberInfo MemberInfoObject memberInfoObject,**/) {
        return ApiResponse.onSuccess(documentService.getAllDocuments(/**memberInfoObject**/));
    }




}
