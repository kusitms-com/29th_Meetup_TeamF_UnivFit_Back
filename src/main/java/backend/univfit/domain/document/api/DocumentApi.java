package backend.univfit.domain.document.api;

import backend.univfit.domain.document.api.dto.CreateDocumentRequest;
import backend.univfit.domain.document.application.DocumentService;
import backend.univfit.global.ApiResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class DocumentApi {
    private final DocumentService documentService;

    @PostMapping("/documents")
    public ApiResponse<?> createDocuments(/**@MemberInfo MemberInfoObject memberInfoObject,**/
                                          @RequestBody CreateDocumentRequest createDocumentRequest) {
        documentService.createDocuments(/**memberInfoObject,**/ createDocumentRequest);
        return ApiResponse.onSuccess("서류가 성공적으로 등록 되었습니다.");
    }

}
