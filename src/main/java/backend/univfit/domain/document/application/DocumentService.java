package backend.univfit.domain.document.application;

import backend.univfit.domain.document.api.dto.request.CreateDocumentRequest;
import backend.univfit.domain.document.api.dto.request.UpdateDocumentRequest;
import backend.univfit.domain.document.api.dto.response.DocumentDetailResponse;
import backend.univfit.domain.document.api.dto.response.DocumentListResponse;
import backend.univfit.domain.document.api.dto.response.DocumentResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;

public interface DocumentService {
    void createDocuments(MemberInfoObject memberInfoObject, CreateDocumentRequest createDocumentRequest);
    DocumentListResponse getAllDocuments(MemberInfoObject memberInfoObject);
    DocumentResponse updateDocument(MemberInfoObject memberInfoObject, UpdateDocumentRequest updateDocumentRequest, Long documentId);
    DocumentDetailResponse getDocument(MemberInfoObject memberInfoObject, Long documentId);
    void deleteDocument(MemberInfoObject memberInfoObject, Long documentId);
}
