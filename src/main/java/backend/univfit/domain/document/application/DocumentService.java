package backend.univfit.domain.document.application;

import backend.univfit.domain.document.api.dto.CreateDocumentRequest;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import org.springframework.web.bind.annotation.RequestBody;

public interface DocumentService {
//    void createDocuments(MemberInfoObject memberInfoObject, CreateDocumentRequest createDocumentRequest);
    void createDocuments(CreateDocumentRequest createDocumentRequest);
}
