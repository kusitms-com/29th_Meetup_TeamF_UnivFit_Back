package backend.univfit.domain.document.application;

import backend.univfit.domain.document.api.dto.request.CreateDocumentRequest;
import backend.univfit.domain.document.api.dto.request.UpdateDocumentRequest;
import backend.univfit.domain.document.api.dto.response.DocumentDetailResponse;
import backend.univfit.domain.document.api.dto.response.DocumentListResponse;
import backend.univfit.domain.document.api.dto.response.DocumentResponse;

public interface DocumentService {
//    void createDocuments(MemberInfoObject memberInfoObject, CreateDocumentRequest createDocumentRequest);
    void createDocuments(CreateDocumentRequest createDocumentRequest);
    DocumentListResponse getAllDocuments(/**@MemberInfo MemberInfoObject memberInfoObject,**/);
    void deleteDocument(/**@MemberInfo MemberInfoObject memberInfoObject,**/Long documentId);
    DocumentResponse updateDocument(UpdateDocumentRequest updateDocumentRequest,/**@MemberInfo MemberInfoObject memberInfoObject,**/Long documentId);
    DocumentDetailResponse getDocument(/**@MemberInfo MemberInfoObject memberInfoObject,**/Long documentId);

}
