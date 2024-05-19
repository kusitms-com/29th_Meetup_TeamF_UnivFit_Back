package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.RequiredDocumentResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;

import java.util.List;

public interface RequiredDocumentService {
    List<RequiredDocumentResponse> getRequiredDocumentList(Long announcementId, MemberInfoObject memberInfoObject);

    RequiredDocumentResponse getRequiredDocument(Long announcementId, Long requiredDocumentId);

}
