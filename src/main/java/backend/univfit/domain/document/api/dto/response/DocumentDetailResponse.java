package backend.univfit.domain.document.api.dto.response;

import java.time.LocalDate;

public record DocumentDetailResponse(
        Long documentId,
        String documentName,
        LocalDate issuedDate,
        String issuer,
        String memo
) {
    public static DocumentDetailResponse of(Long documentId, String documentName, LocalDate issuedDate, String issuer, String memo) {
        return new DocumentDetailResponse(documentId, documentName, issuedDate, issuer, memo);
    }
}
