package backend.univfit.domain.document.api.dto.response;

import java.util.List;

public record DocumentListResponse(
        List<DocumentResponse> documentResponseList
) {
    public static DocumentListResponse of(List<DocumentResponse> documentResponseList) {
        return new DocumentListResponse(documentResponseList);
    }
}
