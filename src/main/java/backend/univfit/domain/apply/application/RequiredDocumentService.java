package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.RequiredDocumentResponse;

import java.util.List;

public interface RequiredDocumentService {
    List<RequiredDocumentResponse> getRequiredDocumentList(Long announcementId);

    RequiredDocumentResponse getRequiredDocument(Long announcementId, Long requiredDocumentId);

}
