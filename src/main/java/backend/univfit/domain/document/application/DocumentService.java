package backend.univfit.domain.document.application;

import backend.univfit.domain.document.api.dto.request.CreateDocumentRequest;
import backend.univfit.domain.document.api.dto.response.DocumentListResponse;

public interface DocumentService {
//    void createDocuments(MemberInfoObject memberInfoObject, CreateDocumentRequest createDocumentRequest);
    void createDocuments(CreateDocumentRequest createDocumentRequest);

    DocumentListResponse getAllDocuments();
}
