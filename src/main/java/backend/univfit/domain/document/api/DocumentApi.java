package backend.univfit.domain.document.api;

import backend.univfit.domain.document.api.dto.request.CreateDocumentRequest;
import backend.univfit.domain.document.api.dto.request.UpdateDocumentRequest;
import backend.univfit.domain.document.api.dto.response.DocumentDetailResponse;
import backend.univfit.domain.document.api.dto.response.DocumentListResponse;
import backend.univfit.domain.document.api.dto.response.DocumentResponse;
import backend.univfit.domain.document.application.DocumentService;
import backend.univfit.global.ApiResponse;
import jakarta.validation.Valid;
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
                                          /*@Valid*/ @RequestBody CreateDocumentRequest createDocumentRequest) {
        documentService.createDocuments(/**memberInfoObject,**/ createDocumentRequest);
        return ApiResponse.onSuccess("서류가 성공적으로 등록 되었습니다.");
    }

    /**
     * 내가 보유한 서류 전체 조회
     * @return
     */
    @GetMapping("/documents")
    public ApiResponse<DocumentListResponse> getAllDocuments(/**@MemberInfo MemberInfoObject memberInfoObject,**/) {
        return ApiResponse.onSuccess(documentService.getAllDocuments(/**memberInfoObject**/));
    }

    /**
     * 내가 보유한 특정 서류 수정
     * @param updateDocumentRequest
     * @param documentId
     * @return
     */
    @PatchMapping("/documents/{documentId}")
    public ApiResponse<DocumentResponse> updateDocument(/**@MemberInfo MemberInfoObject memberInfoObject,**/
                                                        @RequestBody UpdateDocumentRequest updateDocumentRequest,
                                                        @PathVariable Long documentId) {
        return ApiResponse.onSuccess(
                documentService.updateDocument(updateDocumentRequest,/*memberInfoObject,*/ documentId)
        );
    }

    /**
     * 내가 보유한 특정 서류 조회
     * @param documentId
     * @return
     */
    @GetMapping("/documents/{documentId}")
    public ApiResponse<DocumentDetailResponse> getDocument(/**@MemberInfo MemberInfoObject memberInfoObject,**/
                                                           @PathVariable Long documentId) {

        return ApiResponse.onSuccess(documentService.getDocument(/*memberInfoObject*/documentId));
    }

}
